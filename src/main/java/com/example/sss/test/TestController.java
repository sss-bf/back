package com.example.sss.test;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final WebClient webClient;

    public TestController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:9000").build();
    }

    @PostMapping
    public ResponseEntity<?> testUpload(
            @RequestParam("option") String option,
            @RequestParam("url") String url,
            @RequestParam("intend") String intend) {

        System.out.println("Received opt: " + option);
        System.out.println("Received url: " + url);
        System.out.println("Received intend: " + intend);

        try {
            final String endpoint = "2".equals(option) ? "/api/v1/retouching" : "/api/v1/guide";

            String aiResponse = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path(endpoint)
                            .queryParam("user_request", intend)
                            .queryParam("image_url", url)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println(aiResponse);
            return ResponseEntity.status(HttpStatus.OK).body(aiResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "fail", "message", e.getMessage()));
        }
    }
}
