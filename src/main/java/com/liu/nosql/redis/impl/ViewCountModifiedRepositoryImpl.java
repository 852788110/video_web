package com.liu.nosql.redis.impl;

import com.liu.nosql.redis.ViewCountModifiedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCountModifiedRepositoryImpl implements ViewCountModifiedRepository {
    @Value("${viewCount.modifiedQueue}")
    private String modifiedQueue;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set() {
        redisTemplate.opsForSet().add(modifiedQueue,null);
    }

    @Override
    public void delete() {

    }

    @Override
    public void add(String videoId) {
        redisTemplate.opsForSet().add(modifiedQueue,videoId);
    }

    @Override
    public List<String> get() {
        Long count=redisTemplate.opsForSet().size(modifiedQueue);
        if (count!=null&&count>0){
            return redisTemplate.opsForSet().pop(modifiedQueue,count);
        }
        return null;
    }
}
