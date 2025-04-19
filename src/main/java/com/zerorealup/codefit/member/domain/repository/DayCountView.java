package com.zerorealup.codefit.member.domain.repository;

import java.time.LocalDate;

public interface DayCountView {
	LocalDate getDate();
	long      getCount();
}
