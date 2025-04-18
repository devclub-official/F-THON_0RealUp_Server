package com.zerorealup.codefit.member.domain.repository;

import com.zerorealup.codefit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 이 클래스는 멤버 리포지토리를 담당합니다.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
