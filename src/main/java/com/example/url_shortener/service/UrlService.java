package com.example.url_shortener.service;

import com.example.url_shortener.dto.CreateUrlRequest;
import com.example.url_shortener.dto.CreateUrlResponse;
import com.example.url_shortener.entity.Url;
import com.example.url_shortener.exception.ShortUrlNotFoundException;
import com.example.url_shortener.repository.UrlRepository;
import com.example.url_shortener.util.Base62Encoder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private final StringRedisTemplate redisTemplate;

    private final UrlRepository urlRepository;

    public UrlService(
            UrlRepository urlRepository,
            StringRedisTemplate redisTemplate, StringRedisTemplate redisTemplate1, StringRedisTemplate redisTemplate2) {

        this.urlRepository = urlRepository;

        this.redisTemplate = redisTemplate;
    }

    public CreateUrlResponse createShortUrl(CreateUrlRequest request){
        Url url = new Url();

        url.setOriginalUrl(request.getOriginalUrl());
        url.setCreatedAt(LocalDateTime.now());

        Url savedUrl = urlRepository.save(url);

        String shortCode = Base62Encoder.encode(savedUrl.getId());
        String shortUrl = "http://localhost:8080/" + shortCode;

        return new CreateUrlResponse(shortCode,shortUrl);
    }
    public String getOriginalUrl(String shortCode){
        // Check Redis
        String cachedUrl = redisTemplate.opsForValue().get(shortCode);

        //If Found in Redis
        if(cachedUrl != null){
            System.out.println("Redis Cache HIT");
            return cachedUrl;
        }
        //If not found, get ID from short code
        long id = Base62Encoder.decode(shortCode);

        // Get URL from MySQL
        Url url = urlRepository.findById(id)
                .orElseThrow(() ->
                new ShortUrlNotFoundException("Short URL not found"));

        String originalUrl = url.getOriginalUrl();

        // Store result in Redis
        redisTemplate.opsForValue().set(shortCode, originalUrl, Duration.ofMinutes(10));

        System.out.println("Redis Cache MISS - Date loaded from MySQL");

        return originalUrl;
    }

}
