package com.zerorealup.codefit.feedback.domain.repository;

import com.zerorealup.codefit.feedback.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
