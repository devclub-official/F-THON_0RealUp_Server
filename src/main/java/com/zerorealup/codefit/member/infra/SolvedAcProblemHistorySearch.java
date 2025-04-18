package com.zerorealup.codefit.member.infra;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerorealup.codefit.member.app.ProblemHistorySearch;
import com.zerorealup.codefit.member.app.dto.Problem;
import com.zerorealup.codefit.member.infra.dto.SolvedAcProblemResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 이 클래스는 Solved Ac API를 이용해 유저가 푼 문제의 검색을 담당합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SolvedAcProblemHistorySearch implements ProblemHistorySearch {

	/**
	 * 유저가 푼 문제 조회 from the Solved Ac API.
	 *
	 * @param userId user 백준 아이디
	 * @return the response containing solved problems
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public List<Problem> fetchProblems(String userId) throws IOException {
		String apiUrl = "https://solved.ac/api/v3/search/problem?query=s%40" + userId;

		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			log.error("유저가 푼 문제 조회 실패. userId = {}", userId);
			throw new IOException("Failed to fetch data: HTTP error code : " + conn.getResponseCode());
		}

		Scanner scanner = new Scanner(conn.getInputStream());
		StringBuilder jsonBuilder = new StringBuilder();
		while (scanner.hasNext()) {
			jsonBuilder.append(scanner.nextLine());
		}
		scanner.close();
		conn.disconnect();

		ObjectMapper objectMapper = new ObjectMapper();

		SolvedAcProblemResponse response = objectMapper.readValue(jsonBuilder.toString(), SolvedAcProblemResponse.class);

		return response.items().stream()
			.map(item -> new Problem(item.problemId(), item.title(), item.level(), item.tags()))
			.toList();
	}

}
