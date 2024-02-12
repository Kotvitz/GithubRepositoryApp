package com.example.githubrepo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.githubrepo.exception.UserNotFoundException;
import com.example.githubrepo.model.Branch;
import com.example.githubrepo.model.Commit;
import com.example.githubrepo.model.Repository;

import reactor.core.publisher.Mono;

@Service
public class GithubRepositoryService {

	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private final WebClient webClient;

	public GithubRepositoryService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(GITHUB_API_BASE_URL).build();
	}

	public Mono<List<Repository>> getUserRepositories(String username) {
		return webClient.get().uri("/users/{username}/repos", username).retrieve()
				.onStatus(status -> status.equals(HttpStatus.NOT_FOUND),
						response -> response.bodyToMono(String.class).map(UserNotFoundException::new))
				.bodyToFlux(Repository.class).filter(repo -> !repo.isFork()).flatMap(this::enrichWithBranchInfo)
				.collectList();
	}

	private Mono<Repository> enrichWithBranchInfo(Repository repository) {
		String owner = repository.getOwner().getLogin();
		String repoName = repository.getName();

		return webClient.get().uri("/repos/{owner}/{repo}/branches", owner, repoName).retrieve()
				.bodyToFlux(Branch.class).flatMap(branch -> enrichWithLastCommit(branch, owner, repoName)).collectList()
				.map(branches -> {
					repository.setBranches(branches);
					return repository;
				});
	}

	private Mono<Branch> enrichWithLastCommit(Branch branch, String owner, String repoName) {
		return webClient.get().uri("/repos/{owner}/{repo}/branches/{branch}", owner, repoName, branch.getName())
				.retrieve().bodyToMono(Commit.class).map(commit -> {
					branch.setLastCommit(commit);
					return branch;
				});
	}
}
