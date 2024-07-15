package com.medicare.medsystem.service.base;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisListService implements ICacheListService {
    private final ListOperations<String, Object> listOperations;

    public RedisListService(RedisTemplate<String, Object> redisTemplate) {
        this.listOperations = redisTemplate.opsForList();
    }

    public void leftPush(String key, Object value) {
        listOperations.leftPush(key, value);
    }

    public void rightPush(String key, Object value) {
        listOperations.rightPush(key, value);
    }

    public List<Object> getList(String key) {
        return listOperations.range(key, 0, -1);
    }
    public void clearList(String key) {
        listOperations.remove(key, 0, -1);
    }

}
