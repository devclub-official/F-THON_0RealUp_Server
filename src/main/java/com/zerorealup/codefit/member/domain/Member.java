package com.zerorealup.codefit.member.domain;

import com.zerorealup.codefit.member.domain.enums.Level;
import com.zerorealup.codefit.member.domain.enums.ProblemCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 이 클래스는 멤버의 엔티티를 담당합니다.
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "members")
public class Member {
    /** 유저 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "daily_count")
    private Integer dailyCount;

    /* 가장 많이 푼 유형 */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "member_most_solved",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Set<ProblemCategory> mostSolved = new LinkedHashSet<>();

    /* 가장 어려웠던 유형 */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "member_hardest",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Set<ProblemCategory> hardest = new LinkedHashSet<>();

    public void updateOnboarding(
            Level level, int dailyCount,
            Set<ProblemCategory> mostSolved, Set<ProblemCategory> hardest
    ) {
        this.level = level;
        this.dailyCount = dailyCount;
        this.mostSolved.clear();
        this.mostSolved.addAll(mostSolved);
        this.hardest.clear();
        this.hardest.addAll(hardest);
    }
}
