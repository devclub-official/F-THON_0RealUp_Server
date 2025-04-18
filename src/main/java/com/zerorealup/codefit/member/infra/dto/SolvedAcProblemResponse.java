package com.zerorealup.codefit.member.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 이 클래스는 Solved Ac API를 이용해 유저가 푼 문제의 검색을 담당합니다.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SolvedAcProblemResponse(
	@JsonProperty("count") int count,
	@JsonProperty("items") List<Problem> items
) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Problem(
		@JsonProperty("problemId") int problemId,
		@JsonProperty("titleKo") String title,
		@JsonProperty("level") int level,
		@JsonProperty("tags") List<Tag> tags
	) {

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Tag(
		@JsonProperty("bojTagId") int baekjoonId,
		@JsonProperty("key") String solvedIdString,
		@JsonProperty("displayNames") List<DisplayName> displayNames
	) {

		public int solvedId() {
			return solvedIdString.hashCode();
		}

		public String nameKo() {
			return displayNames.stream()
				.filter(d -> d.language().equals("ko"))
				.map(DisplayName::name)
				.findFirst()
				.orElse(null);
		}

		public String nameEng() {
			return displayNames.stream()
				.filter(d -> d.language().equals("en"))
				.map(DisplayName::name)
				.findFirst()
				.orElse(null);
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record DisplayName(
		@JsonProperty("language") String language,
		@JsonProperty("name") String name
	) {

	}
}
