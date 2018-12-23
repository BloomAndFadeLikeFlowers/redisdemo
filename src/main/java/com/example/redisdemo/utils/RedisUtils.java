package com.example.redisdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisUtils {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 通过key获取value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        try {
            result = jedisCluster.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 保存数据
     * @param key
     * @param value
     * @return
     */
    public String set(String key,String value) {
        String result = null;
        try {
            result = jedisCluster.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
