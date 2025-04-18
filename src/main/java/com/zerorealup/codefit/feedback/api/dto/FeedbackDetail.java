package com.zerorealup.codefit.feedback.api.dto;

import com.zerorealup.codefit.feedback.domain.Feedback;

import java.time.LocalDate;
import java.time.ZoneId;

public record FeedbackDetail(
        Long   id,
        String title,
        String category,
        String difficulty,
        LocalDate date,
        String feedback
) {
    public static FeedbackDetail from(Feedback f) {
        return new FeedbackDetail(
                f.getId(),
                f.getProblemTitle(),
                f.getCategory(),
                f.getDifficulty(),
                f.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")).toLocalDate(),
                f.getFeedbackResult()
        );
    }
}
