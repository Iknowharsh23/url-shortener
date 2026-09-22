package com.example.url_shortener.dto;

public class CreateUrlResponse {
    private String shortCode;
    private String shortUrl;

    public CreateUrlResponse(String shortCode, String shortUrl) {
        this.shortCode = shortCode;
        this.shortUrl = shortUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
