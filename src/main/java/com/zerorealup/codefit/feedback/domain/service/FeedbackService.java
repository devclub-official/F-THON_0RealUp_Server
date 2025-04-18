package com.zerorealup.codefit.feedback.domain.service;

import com.zerorealup.codefit.feedback.domain.Feedback;
import com.zerorealup.codefit.feedback.domain.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Transactional
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
