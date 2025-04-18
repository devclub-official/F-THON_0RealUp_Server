package com.zerorealup.codefit.member.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerorealup.codefit.member.infra.dto.SolvedAcProblemResponse.Tag;
import java.util.List;
import lombok.Getter;

/**
 * 이 클래스는 문제를 담당합니다.
 */
public record Problem(

	/**
	 * 백준 문제 번호
	 */
	@JsonProperty("problemId") int problemId,

	/**
	 * 문제 제목
	 */
	@JsonProperty("title") String title,

	/** 문제 난이도 */
	@JsonProperty("level") int level,

	/** 태그들 */
	@JsonProperty("tags") List<Tag> tags
) {

}
