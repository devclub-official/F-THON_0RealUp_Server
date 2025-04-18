package com.zerorealup.codefit.member.domain.repository;

import com.zerorealup.codefit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
