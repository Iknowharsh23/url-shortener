package com.example.url_shortener.controller;

import com.example.url_shortener.dto.CreateUrlRequest;
import com.example.url_shortener.dto.CreateUrlResponse;
import com.example.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/api/urls")
    public CreateUrlResponse createShortUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        return urlService.createShortUrl(request);
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode){
        String originalUrl = urlService.getOriginalUrl(shortCode);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }


}
