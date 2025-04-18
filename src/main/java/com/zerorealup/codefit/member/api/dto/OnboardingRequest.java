package com.zerorealup.codefit.member.api.dto;


import com.zerorealup.codefit.member.domain.enums.Level;
import com.zerorealup.codefit.member.domain.enums.ProblemCategory;

import java.util.Set;

public record OnboardingRequest(
        String       level,       // 한글: 입문, 중급, 상급
        String       dailyCount,  // 문자열로 수신 → int 변환
        Set<String>  mostSolved,  // 영문 카테고리
        Set<String>  hardest      // 영문 카테고리
) {
    public Level levelEnum() {
        return Level.from(level);
    }

    public int dailyCountValue() {
        return Integer.parseInt(dailyCount);
    }

    public Set<ProblemCategory> mostSolvedSet() {
        return mostSolved.stream()
                .map(ProblemCategory::from)
                .collect(java.util.stream.Collectors.toSet());
    }

    public Set<ProblemCategory> hardestSet() {
        return hardest.stream()
                .map(ProblemCategory::from)
                .collect(java.util.stream.Collectors.toSet());
    }
}
