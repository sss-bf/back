package com.example.sss.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public ResponseEntity<?> testUpload(
            @RequestParam("text1") String text1,
            @RequestParam("text2") String text2) {

        try {

            // 응답 JSON 생성
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("receivedText1", text1);
            response.put("receivedText2", text2);

            // JSON 형태로 응답 반환
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "fail"));
        }
    }
}
