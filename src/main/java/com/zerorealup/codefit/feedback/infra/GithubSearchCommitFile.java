package com.zerorealup.codefit.feedback.infra;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerorealup.codefit.feedback.domain.Feedback;
import com.zerorealup.codefit.feedback.domain.repository.FeedbackRepository;
import com.zerorealup.codefit.feedback.infra.dto.GithubCommitResponse;
import com.zerorealup.codefit.feedback.infra.dto.GithubCommitResponse.ChangedFile;
import com.zerorealup.codefit.member.domain.Member;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 이 클래스는 깃허브 파일 검색을 담당합니다.
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class GithubSearchCommitFile {

	private final FeedbackRepository repository;
	private final String GITHUB_API_URL = "https://api.github.com/repos";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public Optional<Feedback>  saveFile(Member member, String owner, String repo, String commitHash)
		throws IOException {

		GithubCommitResponse response = fetchChangedFiles(String.format("%s/%s/%s/commits/%s", GITHUB_API_URL, owner, repo, commitHash));
		Optional<Feedback> feedback= Optional.empty();

		for (ChangedFile file : response.files()) {

			if (file.filename().endsWith(".java")) {
				try {
					feedback = downloadAndSaveGitHubFile(member, file.rawUrl(), file.filename());
				} catch (Exception e) {
					log.error("파일 다운로드 실패", e.getMessage());
				}

			}

			// todo 리드미 파일을 읽어서 처리하는 것도 구현해야함

		}
		return feedback;

	}

	@Transactional
	public Optional<Feedback> downloadAndSaveGitHubFile(Member member, String rawUrl, String filename) throws Exception {
		URL url = new URL(rawUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder contentBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			contentBuilder.append(line).append("\n");
		}
		reader.close();
		conn.disconnect();
		Feedback feedback = Feedback.createFeedback(member , contentBuilder.toString(), filename);
		return Optional.of(repository.save(feedback));
	}

	private GithubCommitResponse fetchChangedFiles(String commitUrl) throws IOException {
		URL apiUrl = new URL(commitUrl);
		HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/vnd.github+json");

		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("Failed: HTTP error code : " + responseCode);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder responseJson = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			responseJson.append(inputLine);
		}
		in.close();
		conn.disconnect();

		return objectMapper.readValue(responseJson.toString(), GithubCommitResponse.class);
	}
}
