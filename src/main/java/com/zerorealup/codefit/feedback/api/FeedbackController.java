package com.zerorealup.codefit.feedback.api;

import com.zerorealup.codefit.feedback.app.FeedbackFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackFacade feedbackFacade;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public String testCreateFeedback(
            @RequestParam("memberId") Long memberId,
            @RequestBody String code
    ) {
        return feedbackFacade.testCodeFeedback(memberId, code);
    }
}
