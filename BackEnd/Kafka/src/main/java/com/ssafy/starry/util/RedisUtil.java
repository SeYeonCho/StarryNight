package com.ssafy.starry.util;

import java.util.Collections;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;


    public void addSet(String key, Object o) {
        redisTemplate.opsForSet().add(key, o);
    }

    public Set<Object> get(String key) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate.opsForSet().intersect(Collections.singleton(key));
    }

    public void setTwit(String key, String value){
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        if(redisTemplate.opsForList().size(key)>5){
            redisTemplate.opsForList().leftPop(key);
        }
        redisTemplate.opsForList().rightPush(key, value);
    }



    public Object get(String key, boolean values) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object o) {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(key, o);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
