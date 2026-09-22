package com.example.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUrlRequest {

    @NotBlank(message = "Original URL cannot be empty")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
