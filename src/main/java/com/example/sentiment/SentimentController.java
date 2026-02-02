package com.example.sentiment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SentimentController {

    @GetMapping("/api/sentiment")
    public Map<String, String> sentiment(@RequestParam("text") String text) {
        String result = "neutral";

        String lower = text.toLowerCase();
        if (lower.contains("good") || lower.contains("love")) {
            result = "positive";
        } else if (lower.contains("bad") || lower.contains("hate")) {
            result = "negative";
        }

        return Map.of("sentiment", result);
    }
}
