package com.zerorealup.codefit.member.domain.service;

import com.zerorealup.codefit.member.api.dto.SolvedCountResponse;
import com.zerorealup.codefit.member.domain.repository.ProblemHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolvedCountQueryService {

    private final ProblemHistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public List<SolvedCountResponse> getDailySolvedCount(Long memberId, int year) {
        return historyRepository.findDailyCountsByMemberAndYear(memberId, year)
                .stream()
                .map(v -> new SolvedCountResponse(v.getDate(), v.getCount()))
                .toList();
    }
}
