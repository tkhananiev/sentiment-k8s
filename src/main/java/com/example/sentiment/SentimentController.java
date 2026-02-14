package com.example.sentiment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SentimentController {

    private final SentimentService service;

    public SentimentController(SentimentService service) {
        this.service = service;
    }

    @GetMapping("/api/sentiment")
    public Map<?, ?> sentiment(@RequestParam("text") String text) {
        return service.predict(text);
    }
}
