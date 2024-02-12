package com.example.githubrepo.model;

public class Branch {
	private String name;
	private String commitSha;

	public Branch() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommitSha() {
		return commitSha;
	}

	public void setCommitSha(String commitSha) {
		this.commitSha = commitSha;
	}
}
