package com.zerorealup.codefit.feedback.api;

import com.zerorealup.codefit.feedback.api.dto.FeedbackDetail;
import com.zerorealup.codefit.feedback.api.dto.FeedbackSummary;
import com.zerorealup.codefit.feedback.app.FeedbackFacade;
import com.zerorealup.codefit.feedback.domain.service.FeedbackQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackFacade feedbackFacade;
    private final FeedbackQueryService feedbackQueryService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String testCreateFeedback(
            @RequestParam("memberId") Long memberId,
            @RequestBody String code
    ) {
        return feedbackFacade.testCodeFeedback(memberId, code);
    }

    @GetMapping("/{feedbackId}")
    public FeedbackDetail getFeedback(
            @PathVariable Long feedbackId
    ) {
        return feedbackQueryService.getFeedback(feedbackId);
    }

    @GetMapping
    public List<FeedbackSummary> getFeedbackList(
            @RequestParam Long memberId
    ) {
        return feedbackQueryService.getFeedbackList(memberId);
    }
}
