package com.zerorealup.codefit.feedback.api.dto;

import com.zerorealup.codefit.feedback.domain.Feedback;

import java.time.LocalDate;
import java.time.ZoneId;

public record FeedbackSummary(
        Long   id,
        String title,
        String category,
        String difficulty,
        LocalDate date
) {
    public static FeedbackSummary from(Feedback f) {
        return new FeedbackSummary(
                f.getId(),
                f.getProblemTitle(),
                f.getCategory(),
                f.getDifficulty(),
                f.getCreatedAt().atZone(ZoneId.of("Asia/Seoul")).toLocalDate()
        );
    }
}
