package com.zerorealup.codefit.feedback.app;

import com.zerorealup.codefit.feedback.domain.service.AiUsageService;
import com.zerorealup.codefit.feedback.infra.NaturalLanguageProcessor;
import com.zerorealup.codefit.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackFacade {
    private final MemberService memberService;
    private final AiUsageService aiUsageService;
    private final NaturalLanguageProcessor processor;

    public String createCodeFeedback(Long memberId, String code) {
        memberService.findExistingMember(memberId);
        aiUsageService.increaseUsage(memberId);
        return processor.createCodeFeedback(code);
    }
}
