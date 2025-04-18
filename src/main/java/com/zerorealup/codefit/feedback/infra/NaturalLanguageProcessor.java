package com.zerorealup.codefit.feedback.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaturalLanguageProcessor {
    private final ChatClient chat;

    private static final String CODE_FEEDBACK_PROMPT = """
        당신은 10년 차 시니어 Java 아키텍트이자 코드 리뷰 컨설턴트입니다.
        
        아래 소스 코드를 면밀히 분석한 **전문 리포트**를 **오직 다음 마크다운 서식**에 맞춰 작성하세요.  
        다른 포맷(JSON 등)·여는/닫는 태그·불필요한 설명은 절대로 포함하지 마십시오.
        
        ## 1. 복잡도 분석
        - **시간 복잡도**: <Big‑O 표기>
        - **공간 복잡도**: <Big‑O 표기>
        
        ## 2. 개선 포인트
        개선 항목이 많은 만큼 반복 작성합니다.  
        각 항목은 `### 개선 포인트 N – <간결한 제목>` 형식의 **레벨 3 제목**으로 시작하고,  
        다음 세 섹션을 반드시 포함하십시오.
        
        ### 개선 포인트 1 – <간결한 제목>
        - **유형**: <성능 / 가독성 / 메모리 / 안정성 / 테스트성 등 한 단어>
        - **개선 방안**:  
          <문제 원인과 해결 전략을 2‑3문장으로 전문적으로 서술>
        - **개선 코드 예시**:
          ```java
          // 핵심 변경 사항만 보여 주세요
          수정된_코드_조각();
          ```
        ### 개선 포인트 2 – <간결한 제목>
        - **유형**:...
        - **개선 방안**:...
        - **개선 코드 예시**:
        (필요 시 “개선 포인트 3, 4, …”를 동일한 형식으로 이어서 작성)
        
        *추가 설명이나 JSON, 다른 형식은 절대 포함하지 마세요.*
        
        ### 검토할 코드
        ```java
        %s
        ```
        """;

    /**
     * @param code 사용자가 입력한 소스 코드
     * @return 복잡도·개선 포인트·예시 코드만 담긴 JSON 문자열
     */
    public String createCodeFeedback(String code) {
        String prompt = String.format(CODE_FEEDBACK_PROMPT, code);

        return chat.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
