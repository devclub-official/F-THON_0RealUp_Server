package com.zerorealup.codefit.feedback.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 이 클래스는 깃허브 파일을 담당합니다.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubCommitResponse(
	@JsonProperty("sha") String sha,
	@JsonProperty("commit") Commit commit,
	@JsonProperty("author") Author author,
	@JsonProperty("committer") Committer committer,
	@JsonProperty("files") List<ChangedFile> files
) {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Commit(
		@JsonProperty("author") CommitPerson author,
		@JsonProperty("committer") CommitPerson committer,
		@JsonProperty("message") String message
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record CommitPerson(
		@JsonProperty("name") String name,
		@JsonProperty("email") String email,
		@JsonProperty("date") String date
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Author(
		@JsonProperty("login") String login,
		@JsonProperty("html_url") String htmlUrl
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Committer(
		@JsonProperty("login") String login,
		@JsonProperty("html_url") String htmlUrl
	) {}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record ChangedFile(
		@JsonProperty("filename") String filename,
		@JsonProperty("status") String status,
		@JsonProperty("additions") int additions,
		@JsonProperty("deletions") int deletions,
		@JsonProperty("changes") int changes,
		@JsonProperty("raw_url") String rawUrl
	) {}
}
