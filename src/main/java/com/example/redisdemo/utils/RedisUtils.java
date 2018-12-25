package com.example.redisdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.Set;

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
     * 通过key获取value
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] result = null;
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

    /**
     * 保存数据
     * @param key
     * @param value
     * @return
     */
    public String set(byte[] key,byte[] value) {
        String result = null;
        try {
            result = jedisCluster.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
