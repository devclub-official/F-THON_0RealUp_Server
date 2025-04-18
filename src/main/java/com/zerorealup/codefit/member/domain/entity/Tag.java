package com.zerorealup.codefit.member.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * 이 클래스는 문제의 태그를 담당합니다.
 */
@Entity
public class Tag {

	/**
	 * 태그 아이디
	 */
	@Id
	@GeneratedValue
	private int id;

	/**
	 * solved ac 에서 사용되는 tag id
	 */
	private int solvedId;


	/**
	 * 백준에서 사용되는 tag id
	 */
	private int baekjoonId;


	/**
	 * 태그 이름(영어)
	 */
	private String nameEng;

	/**
	 * 태그 이름(한글)
	 */
	private String nameKo;

}
