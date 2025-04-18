package com.zerorealup.codefit.feedback.common.exception;

import com.zerorealup.codefit.core.exception.TooManyRequestsException;

import java.time.LocalDate;

import static com.zerorealup.codefit.core.exception.ErrorCode.AI_USAGE_LIMIT_EXCEEDED;

public class MaxAiUsageLimitExceededException extends TooManyRequestsException {
    public MaxAiUsageLimitExceededException(Long memberId, LocalDate date) {
        super(AI_USAGE_LIMIT_EXCEEDED.getMessage().formatted(memberId, date.toString()));
    }

    @Override
    public String getErrorCode() {
        return AI_USAGE_LIMIT_EXCEEDED.name();
    }
}
