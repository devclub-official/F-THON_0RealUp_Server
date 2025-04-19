package com.zerorealup.codefit.member.app;

import com.zerorealup.codefit.member.app.dto.Problem;
import java.io.IOException;
import java.util.List;

/**
 * 이 클래스는 유저의 문제 풀이 기록 조회의 정의를 담당합니다.
 */
public interface ProblemHistorySearch {

	List<Problem> fetchProblems(String userId) throws IOException;
}
