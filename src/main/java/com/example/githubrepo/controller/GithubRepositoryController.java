package com.example.githubrepo.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;

import com.example.githubrepo.model.Repository;
import com.example.githubrepo.service.GithubRepositoryService;

import reactor.core.publisher.Mono;

@RestController
public class GithubRepositoryController {

	private final GithubRepositoryService githubRepositoryService;

	public GithubRepositoryController(GithubRepositoryService githubRepositoryService) {
		this.githubRepositoryService = githubRepositoryService;
	}

	@GetMapping(value = "/repositories/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<List<Repository>> getUserRepositories(@PathVariable String username,
			@RequestHeader(value = "Accept", defaultValue = MediaType.APPLICATION_JSON_VALUE) String acceptHeader) {
		if (!MediaType.APPLICATION_JSON_VALUE.equals(acceptHeader)) {
			return Mono.error(new UnsupportedMediaTypeException("Only 'application/json' content type is supported."));
		}
		return githubRepositoryService.getUserRepositories(username);
	}
}
