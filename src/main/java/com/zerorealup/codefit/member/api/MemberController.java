package com.zerorealup.codefit.member.api;

import com.zerorealup.codefit.member.api.dto.OnboardingRequest;
import com.zerorealup.codefit.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{memberId}/onboarding")
    public void saveOnboarding(
            @PathVariable Long memberId,
            @RequestBody OnboardingRequest request
    ) {
        memberService.updateOnboarding(memberId, request);
    }
}
