package com.zerorealup.codefit.member.domain.enums;

import lombok.Getter;

@Getter
public enum ProblemCategory {
    MATH,
    IMPLEMENTATION,
    GREEDY,
    STRING,
    DATA_STRUCTURES,
    GRAPHS,
    DP,
    GEOMETRY;

    public static ProblemCategory from(String value) {
        return ProblemCategory.valueOf(value.toUpperCase());
    }
}
