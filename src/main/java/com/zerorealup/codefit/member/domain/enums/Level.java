package com.zerorealup.codefit.member.domain.enums;

import lombok.Getter;

@Getter
public enum Level {
    입문,
    중급,
    상급;

    public static Level from(String value) {
        return Level.valueOf(value);
    }
}
