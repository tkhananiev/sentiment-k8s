package com.example.sentiment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class SentimentService {

    private final RestTemplate rt = new RestTemplate();

    @Value("${ai.url:http://localhost:8000}")
    private String aiUrl;

    public Map<?, ?> predict(String text) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(aiUrl)
                .path("/predict")
                .queryParam("text", text)
                .build()              // важно: build() + toUri()
                .toUri();

        return rt.getForObject(uri, Map.class);
    }
}

