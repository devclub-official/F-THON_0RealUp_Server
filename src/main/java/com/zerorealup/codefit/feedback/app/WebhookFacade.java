package com.zerorealup.codefit.feedback.app;

import com.zerorealup.codefit.feedback.infra.GithubSearchCommitFile;
import com.zerorealup.codefit.member.domain.Member;
import com.zerorealup.codefit.member.domain.repository.MemberRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 웹 훅의 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class WebhookFacade {
	private final GithubSearchCommitFile githubSearchCommitFile;
	private final MemberRepository memberRepository;

	public void saveFile(String owner, String repo,  String commitHash) {
		// 유저 정보 조회
		Member member = memberRepository.findByGithubId(owner);

		// 커밋 파일 저장
		try {
			githubSearchCommitFile.saveFile(member, owner, repo,commitHash );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// 피드백 실행

	}
}
