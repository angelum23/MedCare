package com.medicare.medsystem.service.base;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisListService<T> implements ICacheListService<T> {
    private final ListOperations<String, T> listOperations;

    public RedisListService(RedisTemplate<String, T> redisTemplate) {
        this.listOperations = redisTemplate.opsForList();
    }

    public void setList(String key, List<T> value) {
        clearList(key);
        listOperations.rightPushAll(key, value);
    }

    public void leftPush(String key, T value) {
        listOperations.leftPush(key, value);
    }

    public void rightPush(String key, T value) {
        listOperations.rightPush(key, value);
    }

    public List<T> getList(String key) {
        return listOperations.range(key, 0, -1);
    }
    public void clearList(String key) {
        listOperations.remove(key, 0, -1);
    }

}
