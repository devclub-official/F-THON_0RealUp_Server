package com.zerorealup.codefit.member.api;

import com.zerorealup.codefit.member.api.dto.OnboardingRequest;
import com.zerorealup.codefit.member.api.dto.SolvedCountResponse;
import com.zerorealup.codefit.member.app.EmailService;
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
    private final EmailService emailService;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{memberId}/recommendations/email")
    public void sendRecommendations(@PathVariable Long memberId) {

        // 1) 추천 링크 리스트 (실제 로직에서는 알고리즘으로 추출)
        List<String> links = List.of(
                "https://www.acmicpc.net/problem/11404",
                "https://www.acmicpc.net/problem/14499",
                "https://www.acmicpc.net/problem/13549"
        );

        // 2) 회원 이메일 조회
        String email = "gudwls215@gmail.com";

        // 3) 메일 발송
        emailService.sendRecommendation(email, links);
    }
}
