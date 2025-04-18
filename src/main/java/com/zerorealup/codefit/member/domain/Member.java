package com.zerorealup.codefit.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 이 클래스는 멤버의 엔티티를 담당합니다.
 */
@Entity
public class Member {

    /** 유저 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** 하루 목표 */
    @Column(name = "goal", nullable = false)
    private int goal;

    /** 백준 계정 */
    @Column(name = "baekjoon_id", nullable = false)
    private String baekjoonId;

    /** 깃허브 계정 */
    @Column(name = "github_id", nullable = false)
    private String githubId;

    /** 알림을 받을 이메일 */
    @Column(name = "email", nullable = false)
    private String email;
}
