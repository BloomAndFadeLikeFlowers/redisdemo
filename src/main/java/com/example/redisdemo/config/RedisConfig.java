package com.example.redisdemo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 81046 on 2018-08-30
 */
@Configuration
public class RedisConfig {

    @Value("${jedisCluster.nodes}")
    private String clusterNodes;

    @Value("${jedisCluster.password}")
    private String password;

    @Value("${jedisCluster.timeout:20000}")
    private int connectionTimeout;

    @Value("${jedisCluster.pool.max-active:8}")
    private int maxActive;

    @Value("${jedisCluster.pool.max-idle:1000}")
    private int maxIdle;

    @Value("${jedisCluster.pool.min-idle:0}")
    private int minIdle;

    @Value("${jedisCluster.pool.max-wait:-1}")
    private int maxWait;

    @Value("${jedisCluster.soTimeout:2000}")
    private int soTimeout;

    @Value("${jedisCluster.maxAttempts:5}")
    private int maxAttempts;


    @Bean
    public JedisCluster getJedisCluster() {
        //分割集群节点
        String[] cNodes = clusterNodes.split(",");
        //创建set集合对象
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : cNodes) {
            //127.0.0.1:7001
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        System.out.println("---------------------------" + maxActive);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        //创建Redis集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
        return jedisCluster;
    }
}
