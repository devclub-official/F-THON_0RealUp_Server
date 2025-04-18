package com.zerorealup.codefit.member.domain.repository;

import com.zerorealup.codefit.member.domain.ProblemHistory;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemHistoryRepository extends JpaRepository<ProblemHistory, Long> {

	@Query("SELECT p.id FROM ProblemHistory p WHERE p.member.id = :memberId")
	List<Integer> findAllByMemberId(Long memberId);

	@Query(value = """
            SELECT  DATE(created_at)   AS date,
                    COUNT(*)           AS count
            FROM    problem_history
            WHERE   member_id = :memberId
              AND   YEAR(created_at) = :year
            GROUP BY DATE(created_at)
            ORDER BY DATE(created_at)
            """, nativeQuery = true)
	List<DayCountView> findDailyCountsByMemberAndYear(@Param("memberId") Long memberId,
													  @Param("year")     int  year);

}
