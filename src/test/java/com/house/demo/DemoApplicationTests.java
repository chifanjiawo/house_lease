package com.house.demo;

import com.house.demo.utils.ApplicationContextUtil;
import com.house.demo.utils.JwtUtil;
import com.house.demo.model.HouseOrder;
import com.house.demo.service.HouseOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private HouseOrderService orderService;

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

    @Test
    void fun02(){

        HouseOrder order = new HouseOrder();
        order.setHouseAddress("广东");
        order.setHouseCost(1000);
        order.setHouseArea(200);
        order.setHouseDesc("这是一个描述");
        order.setHouseOrientation("朝南");
        order.setHouseEarlyTime(new Date());
        order.setHouseNum((byte) 2);
        order.setHouseRequire("需要很多东西");
        order.setHouseDecoration("精装修");
        order.setHousePayment("压二付一");
        order.setHouseUserId(5);
        order.setHouseType("类型");

        orderService.createHouseOrder(order);
    }

}
