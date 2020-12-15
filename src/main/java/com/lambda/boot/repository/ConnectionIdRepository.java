package com.lambda.boot.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionIdRepository {
    private static final String KEY = "CONNECTION_IDS";
    private final HashOperations<String, String, String> hashOperations;

    public ConnectionIdRepository(RedisTemplate<String, String> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public String put(String key, String inputValue) {
        hashOperations.put(KEY, key, inputValue);
        return inputValue;
    }

    public String getByKey(String key) {
        return hashOperations.get(KEY, key);
    }
}
