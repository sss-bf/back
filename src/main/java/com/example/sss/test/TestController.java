package com.example.sss.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    // 보안그룹 내 아이피로 변경 후 다시
    // Github Action에서 사용하는 IP대역이 있어서 수정 후 다시
    // #1. Bastion에 known_host 등록
    // #11. docker rm stop 되는지 확인
    // #14. ecr repo 로그인 잘 되는지 확인
    @GetMapping
    public String test() {
        return "CI/CD Success!!!(3)";
    }
}

//package com.example.sss.test;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    @PostMapping
//    public ResponseEntity<String> test(@RequestBody Map<String, String> requestData) {
//        String url = requestData.get("url");
//        String intent = requestData.get("intent");
//
//        if (url == null || intent == null) {
//            return ResponseEntity.badRequest().body("url 또는 intent가 누락되었습니다.");
//        }
//
//        return ResponseEntity.ok("Received URL: " + url + ", Intent: " + intent);
//    }
////    private final WebClient webClient;
////
////    public TestController(WebClient.Builder webClientBuilder) {
////        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:9000").build();
////    }
////
////    @PostMapping
////    public ResponseEntity<?> testUpload(
//////            @RequestParam("option") String option,
////            @RequestParam("url") String url,
////            @RequestParam("intend") String intend) {
////
//////        System.out.println("Received opt: " + option);
////        System.out.println("Received url: " + url);
////        System.out.println("Received intend: " + intend);
////
////        Map<String, String> response = new HashMap<>();
////        response.put("status", "success");
////        response.put("receivedText1", url);
////        response.put("receivedText2", intend);
//
////        try {
////            final String endpoint = "2".equals(option) ? "/api/v1/retouching" : "/api/v1/guide";
////            System.out.println(endpoint);
////            String aiResponse = webClient.post()
////                    .uri(uriBuilder -> uriBuilder
////                            .path(endpoint)
////                            .queryParam("user_request", intend)
////                            .queryParam("image_url", url)
////                            .build())
////                    .retrieve()
////                    .bodyToMono(String.class)
////                    .block();
////
////            System.out.println(aiResponse);
//
////        return ResponseEntity.status(HttpStatus.OK).body(response);
//
////        } catch (Exception e) {
////            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
////                    .body(Map.of("status", "fail", "message", e.getMessage()));
////        }
//    }
//
