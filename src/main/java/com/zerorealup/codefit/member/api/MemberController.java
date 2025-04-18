package com.zerorealup.codefit.member.api;

import com.zerorealup.codefit.member.api.dto.OnboardingRequest;
import com.zerorealup.codefit.member.api.dto.SolvedCountResponse;
import com.zerorealup.codefit.member.domain.service.MemberService;
import com.zerorealup.codefit.member.domain.service.SolvedCountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final SolvedCountQueryService solvedCountQueryService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{memberId}/onboarding")
    public void saveOnboarding(
            @PathVariable Long memberId,
            @RequestBody OnboardingRequest request
    ) {
        memberService.updateOnboarding(memberId, request);
    }

    @GetMapping("/{memberId}/solved-counts")
    public List<SolvedCountResponse> getSolvedCounts(
            @PathVariable Long memberId,
            @RequestParam  int year
    ) {
        return solvedCountQueryService.getDailySolvedCount(memberId, year);
    }
}
