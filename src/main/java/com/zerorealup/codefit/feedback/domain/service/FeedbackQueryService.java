package com.zerorealup.codefit.feedback.domain.service;

import com.zerorealup.codefit.feedback.api.dto.FeedbackDetail;
import com.zerorealup.codefit.feedback.api.dto.FeedbackSummary;
import com.zerorealup.codefit.feedback.common.exception.NotFoundFeedbackException;
import com.zerorealup.codefit.feedback.domain.Feedback;
import com.zerorealup.codefit.feedback.domain.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackQueryService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackDetail getFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new NotFoundFeedbackException(feedbackId));
        return FeedbackDetail.from(feedback);
    }

    public List<FeedbackSummary> getFeedbackList(Long memberId) {
        return feedbackRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId)
                .stream()
                .map(FeedbackSummary::from)
                .toList();
    }
}
