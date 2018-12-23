package com.example.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.redisdemo.Dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping(value = "jedisCluster")
@Api(value = "jedisCluster操作redis集群")
public class JedisClusterTestController {
    @Autowired
    JedisCluster jedisCluster;


    @ApiOperation(value = "通过key获取value")
    @ApiImplicitParam(name = "key", value = "key", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "redisTemplete/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable(value = "key") String key) {

        String name = jedisCluster.get(key);
        return "success " + name;
    }

    @ApiOperation(value = "保存key，value")
    @ApiImplicitParam(name = "user", value = "保存用户", paramType = "path", required = true, dataType = "Map")
    @RequestMapping(value = "set", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String save(@RequestBody User user) {

        String name = jedisCluster.set(user.getSno(),JSON.toJSONString(user));
        return "success " + name;
    }
}
