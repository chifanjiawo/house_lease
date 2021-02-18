package com.house.demo;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        HouseUser user = new HouseUser();

        user.setUserId(1);
        user.setUserName("xjj");


        String s = JSONObject.toJSONString(user);

        System.out.println(s);

        HouseUser object = JSONObject.parseObject(s,HouseUser.class);

        System.out.println(object);

    }

}
