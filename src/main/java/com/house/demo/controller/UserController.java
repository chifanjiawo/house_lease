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

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String userLogin(){



    return "";
    }



//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }



}
