package com.sgst.controller;

import com.sgst.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("setOpsForValue")
    @ApiOperation(tags = {"redis"}, value = "setOpsForValue")
    R put(@RequestParam String key, @RequestParam String value,@RequestParam Integer timeout){
        redisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);
        return R.ok();
    }

    @GetMapping("getOpsForValue")
    @ApiOperation(tags = {"redis"}, value = "getOpsForValue")
    String getOpsForValue(@RequestParam String key){
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("list")
    @ApiOperation(tags = {"redis"}, value = "获取所有key")
    Set<String> getKeyList(){
        return redisTemplate.keys("*");
    }
}
