package com.example.githubrepo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.githubrepo.model.Repository;
import com.example.githubrepo.service.GithubRepositoryService;

import reactor.core.publisher.Mono;

@RestController
public class GithubRepositoryController {

	private final GithubRepositoryService githubRepositoryService;

	public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
		this.githubRepositoryService = githubRepositoryService;
	}

	@GetMapping("/repositories/{username}")
	public Mono<List<Repository>> getUserRepositories(@PathVariable String username) {
		return githubRepositoryService.getUserRepositories(username);
	}
}
