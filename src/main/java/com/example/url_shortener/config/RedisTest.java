package com.example.url_shortener.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest implements CommandLineRunner {

    private final StringRedisTemplate redisTemplate;

    public RedisTest(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Override
    public void run(String... args){

        redisTemplate.opsForValue().set("test","Hello Redis");

        String value = redisTemplate.opsForValue().get("test");

        System.out.println("Redis Value: " + value);
    }
}
