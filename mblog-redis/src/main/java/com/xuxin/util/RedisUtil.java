package com.xuxin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public final class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private RedisUtil(){}

    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 保存数据
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 保存数据，过期时间单位为分钟
     * @param key
     * @param value
     * @param timeout
     */
    public void setExpireByMin(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);
    }

    /**
     * 保存数据，过期时间单位为小时
     * @param key
     * @param value
     * @param timeout
     */
    public void setExpireByHour(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.HOURS);
    }

    /**
     * 保存数据，过期时间单位为天
     * @param key
     * @param value
     * @param timeout
     */
    public void setExpireByDay(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.DAYS);
    }
}
