package com.zerorealup.codefit.member.domain;

import com.zerorealup.codefit.core.domain.BaseTimeEntity;
import com.zerorealup.codefit.member.app.dto.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 푼 문제의 기록을 담당합니다.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemHistory extends BaseTimeEntity {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * 문제 제목
	 */
	private String title;

	/**
	 * 문제 난이도
	 */
	private int level;


	/**
	 * 문제 태그
	private List<Tag> tags = new ArrayList<>();
	 */

	public static ProblemHistory of(Member member, Problem problem) {
		return ProblemHistory.builder()
				.member(member)
				.title(problem.title())
				.level(problem.level())
				.build();
	}
}
