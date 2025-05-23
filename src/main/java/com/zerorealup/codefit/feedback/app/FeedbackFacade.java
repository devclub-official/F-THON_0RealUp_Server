package com.zerorealup.codefit.feedback.app;

import com.zerorealup.codefit.feedback.domain.Feedback;
import com.zerorealup.codefit.feedback.domain.service.AiUsageService;
import com.zerorealup.codefit.feedback.domain.service.FeedbackService;
import com.zerorealup.codefit.feedback.infra.NaturalLanguageProcessor;
import com.zerorealup.codefit.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackFacade {
    private final MemberService memberService;
    private final FeedbackService feedbackService;
    private final AiUsageService aiUsageService;
    private final NaturalLanguageProcessor processor;

    // todo: event로 받아 처리
    public Feedback createCodeFeedback(
            Feedback feedback
    ) {
        memberService.findExistingMember(feedback.getMemberId());
        aiUsageService.increaseUsage(feedback.getId());

        String feedbackResult = processor.createCodeFeedback(feedback.getCode());
        feedback.writeFeedback(feedbackResult);

        return feedbackService.save(feedback);
    }

    public String testCodeFeedback(
            Long memberId, String code
    ) {
        memberService.findExistingMember(memberId);
        aiUsageService.increaseUsage(memberId);

        return processor.createCodeFeedback(code);
    }
}
