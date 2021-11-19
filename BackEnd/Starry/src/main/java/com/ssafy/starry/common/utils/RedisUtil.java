package com.ssafy.starry.common.utils;


import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    public void addSet(String key, Object o) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForSet().add(key, o);
    }

    public Object get(String key) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate.opsForValue().get(key);
    }

    public List<Object> getTwit(String key){
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate.opsForList().range(key,0,4);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
