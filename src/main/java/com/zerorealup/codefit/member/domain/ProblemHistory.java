package com.zerorealup.codefit.member.domain;

import com.zerorealup.codefit.member.app.dto.Problem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 푼 문제의 기록을 담당합니다.
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemHistory {

	/**
	 * id
	 */
	@GeneratedValue @Id
	private int problemId;


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
		return new ProblemHistory(0, member, problem.title(), problem.level());
	}


}
