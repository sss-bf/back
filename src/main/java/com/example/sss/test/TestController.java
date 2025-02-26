package com.example.sss.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/test")  // "/test" 경로로 설정
public class TestController {

    @PostMapping
    public ResponseEntity<?> testUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("text") String text) {

        try {
            // 파일과 텍스트 데이터 처리
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Received text: " + text);

            // 정상 처리 후 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("fail");
        }
    }
}
