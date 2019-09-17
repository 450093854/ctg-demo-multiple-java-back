package com.sgst;

import com.sgst.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTestApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mysqlTest() {
        // 查询user表中总记录数
        System.out.println("user表总记录数为："+userMapper.selectCount(null));
    }

    @Test
    public void redisTest(){
        // 查询redis第一个数据库中所有的key
        Set<String> keys = redisTemplate.keys("*");
        System.out.println("redis中key总记录数为"+keys.size());
    }

}
