package com.zerorealup.codefit.feedback.common.exception;

import com.zerorealup.codefit.core.exception.NotFoundException;

import static com.zerorealup.codefit.core.exception.ErrorCode.NOT_FOUND_FEEDBACK;

public class NotFoundFeedbackException extends NotFoundException {
    public NotFoundFeedbackException(Long id) {
        super(NOT_FOUND_FEEDBACK.getMessage().formatted(id));
    }

    @Override
    public String getErrorCode() {
        return NOT_FOUND_FEEDBACK.name();
    }
}
