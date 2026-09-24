package com.example.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateUrlRequest {

    @NotBlank(message = "Original URL cannot be empty")
    @Pattern(
            regexp =  "(https?://).+",
            message = "Please enter a vlid URl"
    )
    private String originalUrl;

    public String getOriginalUrl() {

        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {

        this.originalUrl = originalUrl;
    }
}
