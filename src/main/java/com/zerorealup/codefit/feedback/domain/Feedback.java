package com.zerorealup.codefit.feedback.domain;

import com.zerorealup.codefit.core.domain.BaseTimeEntity;
import com.zerorealup.codefit.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "feedbacks")
public class Feedback extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @Column(name = "problem_title")
    private String problemTitle;

    @Column(name = "category")
    private String category;

    @Column(name = "difficulty", length = 20)
    private String difficulty;

    @Column(name = "filename", nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Lob
    @Column(name = "code", columnDefinition = "TEXT", nullable = false)
    private String code;

    @Lob
    @Column(name = "feedback_result")
    private String feedbackResult;

    public static Feedback createFeedback(String problemTitle, String difficulty, String feedbackResult) {
        return Feedback.builder()
                .problemTitle(problemTitle)
                .difficulty(difficulty)
                .feedbackResult(feedbackResult)
                .build();
    }

    public static Feedback createFeedback(Member member, String code, String fileName) {
        return Feedback.builder()
                .member(member)
                .code(code)
                .fileName(fileName)
                .build();
    }
}
