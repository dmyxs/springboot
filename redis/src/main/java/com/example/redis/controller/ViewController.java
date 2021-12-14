package com.example.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ViewController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/view/{articleId}")
    public void view(@PathVariable int articleId){
        String key = "article:" + articleId;
        long n = stringRedisTemplate.opsForValue().increment(key);
        log.info("key={}, 阅读量={}", key, n);
    }
}
