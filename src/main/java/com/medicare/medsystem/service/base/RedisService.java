package com.medicare.medsystem.service.base;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService<T> implements ICacheService<T> {
    private final RedisTemplate<String, T> redisTemplate;

    public RedisService(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
