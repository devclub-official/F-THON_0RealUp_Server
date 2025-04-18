package com.zerorealup.codefit.member.domain.service;

import com.zerorealup.codefit.member.common.exception.NotFoundMemberException;
import com.zerorealup.codefit.member.domain.Member;
import com.zerorealup.codefit.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findExistingMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundMemberException(memberId));
    }
}
