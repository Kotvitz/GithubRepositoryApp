package com.example.githubrepo.model;

public class Branch {
	private String name;
	private Commit lastCommit;

	public Branch() {

	}

	public String getName() {
		return name;
	}

	public Commit getLastCommit() {
		return lastCommit;
	}

	public void setLastCommit(Commit lastCommit) {
		this.lastCommit = lastCommit;
	}

	public void setName(String name) {
		this.name = name;
	}
}
