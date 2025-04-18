package com.zerorealup.codefit.feedback.domain.service;

import com.zerorealup.codefit.feedback.domain.AiUsage;
import com.zerorealup.codefit.feedback.domain.repository.AiUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AiUsageService {
    private final AiUsageRepository repo;

    /** 호출 1회당 사용량 +1 */
    @Transactional
    public void increaseUsage(Long memberId) {
        LocalDate today = LocalDate.now();

        repo.findByMemberIdAndUsageDate(memberId, today)
                .ifPresentOrElse(AiUsage::increase,
                        () -> repo.save(AiUsage.newForToday(memberId, today)));
    }
}
