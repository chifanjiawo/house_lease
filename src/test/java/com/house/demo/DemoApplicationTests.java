package com.house.demo;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.utils.ApplicationContextUtil;
import com.house.demo.common.utils.JwtUtil;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private JwtUtil jwtUtil;



    @Test
    void contextLoads() {

        RedisTemplate redisTemplate = ApplicationContextUtil.getBean("redisTemplate");

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());


        System.out.println(redisTemplate.opsForValue().get("xi"));

    }

    @Test
    void fun01(){
        JwtUtil jwtUtil = ApplicationContextUtil.getBean("jwtUtil");
        System.out.println(jwtUtil==this.jwtUtil);

    }

}
