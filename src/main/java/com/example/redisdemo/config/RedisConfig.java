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

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private String max_redirects;

    @Value("${spring.redis.password}")
    private String password;

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
        //创建Redis集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes,5000,1000,1,password,new GenericObjectPoolConfig());
        return jedisCluster;
    }
}
