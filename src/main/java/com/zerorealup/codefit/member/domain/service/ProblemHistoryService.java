package com.zerorealup.codefit.member.domain.service;

import com.zerorealup.codefit.member.app.ProblemHistorySearch;
import com.zerorealup.codefit.member.app.dto.Problem;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 문제 히스토리의 저장을 담당합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProblemHistoryService{

	private final ProblemHistorySearch search;

	public List<Problem> getProblemHistory(String userId) {
		List<Problem> problems = new ArrayList<>();
		try {
			problems = search.fetchProblems(userId);
		} catch (Exception e) {
			log.error("조회 실패", e.getMessage());
		}
		return problems;
	}
}
