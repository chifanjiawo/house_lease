package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xjj
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private HouseUserMapper userMapper;

    @GetMapping("/login")
    public String userLogin(@RequestParam("name")String name){

        HouseUser user = new HouseUser();

        user.setUserName(name);

        int i = userMapper.insert(user);

        return String.valueOf(i);


    }

    @GetMapping("/select")
    public String userSelect(@RequestParam("id")int id){

        HouseUser user = userMapper.selectById(id);

        return JSONObject.toJSONString(MyResult.succ(user));
    }

//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }



}
