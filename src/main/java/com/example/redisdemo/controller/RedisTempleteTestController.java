package com.example.redisdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "redisTemplete")
@Api(value = "redisTemplete操作redis集群")
public class RedisTempleteTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "通过key获取value")
    @ApiImplicitParam(name = "key", value = "key", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "redisTemplete/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable(value = "key") String key) {

        String name = redisTemplate.opsForValue().get(key);
        return "success " + name;
    }


}
