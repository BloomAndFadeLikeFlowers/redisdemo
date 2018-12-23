package com.example.redisdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisUtils {

    @Autowired
    private JedisCluster jedisCluster;

    public String get(String key) {
        String result = null;
        try {
            result = jedisCluster.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
