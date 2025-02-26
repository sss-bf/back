package com.example.sss.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final WebClient webClient;

    // WebClient를 생성자로 주입
    public TestController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:9000").build();
    }

    @PostMapping
    public ResponseEntity<?> testUpload(
            @RequestParam("text1") String text1,
            @RequestParam("text2") String text2) {

        // 임시로 받은 데이터 로그로 출력
        System.out.println("Received text1: " + text1);
        System.out.println("Received text2: " + text2);

        try {
            // AI 서버에 보내기 위한 데이터 준비
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text1", text1);
            requestBody.put("text2", text2);

            // WebClient를 사용하여 AI 서버에 POST 요청 보내기
            Map<String, String> aiResponse = webClient.post()
                    .uri("/test") // AI 서버의 엔드포인트
                    .bodyValue(requestBody) // 요청 데이터
                    .retrieve() // 요청 수행
                    .bodyToMono(Map.class) // 응답을 Map 형식으로 변환
                    .block(); // 동기 방식으로 응답을 기다림

            // AI 서버에서 받은 응답을 그대로 클라이언트에 반환
            return ResponseEntity.status(HttpStatus.OK).body(aiResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "fail", "message", e.getMessage()));
        }
    }
}
