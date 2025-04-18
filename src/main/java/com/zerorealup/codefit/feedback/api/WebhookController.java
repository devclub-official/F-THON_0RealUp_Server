package com.zerorealup.codefit.feedback.api;

import com.zerorealup.codefit.core.constant.UrlPath.Webhook;
import com.zerorealup.codefit.feedback.app.WebhookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이 클래스는 웹 훅 컨트롤러를 담당합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Webhook.ROOT)
public class WebhookController {
	private final WebhookFacade webhookFacade;

	@GetMapping
	public void saveCode(
		@RequestParam("commitHash") String commitHash,
		@RequestParam("owner") String owner,
		@RequestParam("repo") String repo

	) {
		webhookFacade.saveFile(owner, repo, commitHash);
	}

}
