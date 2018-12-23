package com.example.redisdemo.controller;

import com.example.redisdemo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "redisUtils")
@Api(value = "redisUtils操作redis集群")
public class RedisUtilsTestController {
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "通过key获取value")
    @ApiImplicitParam(name = "key", value = "key", paramType = "path", required = true, dataType = "String")
    @RequestMapping(value = "redisTemplete/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable(value = "key") String key) {

        String name = redisUtils.get(key);
        return "success " + name;
    }
}
