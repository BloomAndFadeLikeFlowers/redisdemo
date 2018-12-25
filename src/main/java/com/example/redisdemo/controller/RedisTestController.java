package com.example.redisdemo.controller;

import com.example.redisdemo.utils.CollectionsUtils11;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "redis")
public class RedisTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    JedisCluster jedisCluster;

    @RequestMapping(value = "cluster")
    public String cluster() {

        String name = "";
        redisTemplate.opsForValue().set("name", "lushangshang");
        name = redisTemplate.opsForValue().get("name");
        redisTemplate.opsForValue().set("key", "value");
        String key = redisTemplate.opsForValue().get("key");
        return "success " + name + key;
    }

    @RequestMapping(value = "cluster2")
    public String cluster2() {


        String name = "";
        Set<String> stringSet = new HashSet<String>();
        stringSet.add("S01");
        stringSet.add("S02");
        StringUtils.toStringArray(stringSet);

        String[] stringArray = CollectionsUtils11.toArray(stringSet);
        System.out.println(stringArray);
//        name = jedisCluster.get("name");
        return "success " + name;
    }
}
