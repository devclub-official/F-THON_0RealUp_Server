package com.zerorealup.codefit.member.domain.repository;

import com.zerorealup.codefit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이 클래스는 멤버 리포지토리를 담당합니다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
