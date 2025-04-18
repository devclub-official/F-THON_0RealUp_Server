package com.zerorealup.codefit.core.constant;

/**
 * 이 클래스는 URL Path 정의을 담당합니다.
 */
public class UrlPath {
	public static final String REST_PREFIX = "/api";
	public static final String REST_VERSION = "/v1";

	public static final class Member {

		public static final String ROOT = REST_PREFIX + REST_VERSION + "/member";
		public static final String LOGIN = "/login";
		public static final String LOGOUT = "/logout";

	}
	public static final class Feedback {
		public static final String ROOT = REST_PREFIX + REST_VERSION + "/feedback";

	}
	public static final class Problem {
		public static final String ROOT = REST_PREFIX + REST_VERSION + "/problem";

	}
}
