package com.zerorealup.codefit.feedback.domain;

import com.zerorealup.codefit.core.domain.BaseTimeEntity;
import com.zerorealup.codefit.feedback.common.exception.MaxAiUsageLimitExceededException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "ai_usages")
public class AiUsage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usage_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "count", nullable = false)
    private Integer count;

    public static AiUsage newForToday(Long memberId, LocalDate date) {
        return AiUsage.builder()
                .memberId(memberId)
                .usageDate(date)
                .count(1)
                .build();
    }

    public void increase() {
        if (this.count >= 100) {
            throw new MaxAiUsageLimitExceededException(memberId, LocalDate.now());
        }
        this.count++;
    }
}
