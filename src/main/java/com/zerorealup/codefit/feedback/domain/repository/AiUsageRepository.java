package com.zerorealup.codefit.feedback.domain.repository;

import com.zerorealup.codefit.feedback.domain.AiUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AiUsageRepository extends JpaRepository<AiUsage, Long> {
    Optional<AiUsage> findByMemberIdAndUsageDate(Long memberId, LocalDate usageDate);
}
