package com.liu.nosql.redis.impl;

import com.liu.nosql.redis.VideoViewCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/*
 *   redis操作service的实现类
 *   用于实现视频的播放量
 * */
@Service
public class VideoViewCountRepositoryImpl implements VideoViewCountRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long increment(String key, Long delta) {
        if (redisTemplate.opsForValue().get(key)==null){
            redisTemplate.opsForValue().set(key,"0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }
}
