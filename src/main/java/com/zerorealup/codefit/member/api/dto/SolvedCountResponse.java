package com.zerorealup.codefit.member.api.dto;

import java.time.LocalDate;

public record SolvedCountResponse(
        LocalDate date,
        long      count
) {
}
