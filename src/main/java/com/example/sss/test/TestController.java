package com.example.sss.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public ResponseEntity<?> testUpload(
            @RequestParam("text1") String text1,
            @RequestParam("text2") String text2) {
        System.out.println("Received text1: " + text1);
        System.out.println("Received text2: " + text2);


        try {
            // AI 서버에 보내기 위한 데이터 준비
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text1", text1);
            requestBody.put("text2", text2);

            // AI 서버 URL (여기서 POST 요청을 보내는 URL)
            String aiServerUrl = "http://127.0.0.1:9000";

            // HTTP 헤더 설정 (필요한 경우)
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // 요청 바디와 헤더를 포함한 HttpEntity 생성
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // RestTemplate 사용하여 AI 서버에 POST 요청 보내기
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> aiResponse = restTemplate.postForObject(aiServerUrl, requestEntity, Map.class);

            // AI 서버에서 받은 응답을 그대로 클라이언트에 반환
            return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "fail", "message", e.getMessage()));
        }
    }
}
