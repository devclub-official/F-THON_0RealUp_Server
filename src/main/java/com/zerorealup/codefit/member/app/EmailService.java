package com.zerorealup.codefit.member.app;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender      mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendRecommendation(String toEmail, List<String> links) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setSubject("[CodeFit] 한 달간 추천 문제 3선");
            helper.setTo(toEmail);

            Context ctx = new Context();
            ctx.setVariable("links", links);

            String html = templateEngine.process("recommendation", ctx);
            helper.setText(html, true); // true = HTML

            mailSender.send(message);
        } catch (Exception e) {
            throw new IllegalStateException("메일 발송 실패", e);
        }
    }
}
