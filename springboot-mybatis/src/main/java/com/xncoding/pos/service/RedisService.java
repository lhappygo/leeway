package com.xncoding.pos.service;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private ReentrantLock lock;
    public String get(String key) {
        String json = "";
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            json = operations.get(key);
            logger.info("从缓存中获取了用户key = " + key);
        }
        return json;
    }
    
    
    public void set(String key,String value){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }
}
