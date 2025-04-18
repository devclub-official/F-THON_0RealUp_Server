package com.zerorealup.codefit.member.common.exception;

import com.zerorealup.codefit.core.exception.NotFoundException;

import static com.zerorealup.codefit.core.exception.ErrorCode.NOT_FOUND_MEMBER;

public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException(Long memberId) {
        super(NOT_FOUND_MEMBER.getMessage().formatted(memberId));
    }

    @Override
    public String getErrorCode() {
        return NOT_FOUND_MEMBER.name();
    }
}
