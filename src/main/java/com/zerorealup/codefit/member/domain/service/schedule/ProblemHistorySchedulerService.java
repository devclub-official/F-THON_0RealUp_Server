package com.zerorealup.codefit.member.domain.service.schedule;

import com.zerorealup.codefit.member.app.dto.Problem;
import com.zerorealup.codefit.member.domain.Member;
import com.zerorealup.codefit.member.domain.ProblemHistory;
import com.zerorealup.codefit.member.domain.repository.MemberRepository;
import com.zerorealup.codefit.member.domain.repository.ProblemHistoryRepository;
import com.zerorealup.codefit.member.domain.service.ProblemHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 이 클래스는 []를 담당합니다.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ProblemHistorySchedulerService {

	private final ProblemHistoryService problemHistoryService;
	private final MemberRepository memberRepository;
	private final ProblemHistoryRepository problemHistoryRepository;

	/**
	 * 매일 오전 3시에 ProblemHistory를 업데이트합니다.
	 */
	@Scheduled(cron = "*/10 0 * * * *")
	public void fetchProblemHistories() {
		try {
			// user id 조회
			for (Member member : memberRepository.findAll()) {

				// member 의 푼 문제 조회
				List<Problem> problems = problemHistoryService.getProblemHistory(member.getBaekjoonId());

				// db에 저장
				List<Integer> problemHistories = problemHistoryRepository.findAllByMemberId(member.getId());

				List<ProblemHistory> newProblems = problems.stream()
					.filter(p -> !problemHistories.contains(p.problemId()))
					.map(p -> ProblemHistory.of(member, p))
					.toList();

				problemHistoryRepository.saveAll(newProblems);
			}
		} catch (Exception e) {
			log.error("문제 히스토리 조회 실패", e);
		}
	}
}