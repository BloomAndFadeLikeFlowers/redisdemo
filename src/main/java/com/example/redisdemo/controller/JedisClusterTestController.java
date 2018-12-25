package com.example.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.redisdemo.Dao.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

import java.util.*;

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


    @ApiOperation(value = "listObject")
    @RequestMapping(value = "listObject", method = RequestMethod.POST)
    @ResponseBody
    public String listObject() {

        List<Object> list = Arrays.asList(
                new User("list01", "张三", 10),
                "String",
                800
        );
        String name = jedisCluster.set("list<Object>", JSON.toJSONString(list));
        String value = jedisCluster.get("list<Object>");
        List<Object> list2 = JSONObject.parseObject(value,ArrayList.class);

        for(Object object:list2){
            System.out.println(object);
        }
        User user =(User) JSONObject.parseObject(JSON.toJSONString(list2.get(0)),User.class);
        System.out.println(user.getSno()+"\t"+user.getSname()+"\t"+user.getAge());
        return name + "\t" + value;

    }

    @ApiOperation(value = "setObject")
    @RequestMapping(value = "setObject", method = RequestMethod.POST)
    @ResponseBody
    public String setObject() {

        Set<Object> set = new HashSet<>();
        set.addAll(Arrays.asList(new User("list01", "张三", 10),"String",800));
        String name = jedisCluster.set("set<Object>", JSON.toJSONString(set));

        String value = jedisCluster.get("set<Object>");
        return name + "\t" + value;

    }

    @ApiOperation(value = "mapStringObject")
    @RequestMapping(value = "mapStringObject", method = RequestMethod.POST)
    @ResponseBody
    public String mapStringObject() {

       Map<String,Object> map = new HashMap<>();
       map.put("user",new User("list01", "张三", 10));
       map.put("string","hello");
        String name = jedisCluster.set("Map<String,Object>", JSON.toJSONString(map));
        String value = jedisCluster.get("Map<String,Object>");
        return name + "\t" + value;

    }

    /**
     * {"list":[{"sno":"s01","sname":"张三","age":18},{"sno":"s02","sname":"李四","age":20}],"key":"sno"}
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "保存key，value")
    @ApiImplicitParam(name = "user", value = "保存用户\r\n{\"sno\":\"s01\",\"sname\":\"张三\",\"age\":18}", required = true, dataType = "Map")
    @RequestMapping(value = "set", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody Map map) {

        String name = jedisCluster.set("map", JSON.toJSONString(map));
        return "success " + name;
    }
}
