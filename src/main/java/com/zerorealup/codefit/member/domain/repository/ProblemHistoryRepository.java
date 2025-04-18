package com.zerorealup.codefit.member.domain.repository;

import com.zerorealup.codefit.member.domain.ProblemHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 이 클래스는 문제 기록의 레포지토리를 담당합니다.
 */
@Repository
public interface ProblemHistoryRepository extends JpaRepository<ProblemHistory, Long> {

	@Query("SELECT p.problemId FROM ProblemHistory p WHERE p.member.id = :memberId")
	List<Integer> findAllByMemberId(int memberId);
}
