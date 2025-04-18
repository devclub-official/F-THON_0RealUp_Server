package com.zerorealup.codefit.feedback.domain.repository;

import com.zerorealup.codefit.feedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByMemberIdOrderByCreatedAtDesc(Long memberId);
}
