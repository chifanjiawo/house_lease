package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;

import com.house.demo.common.utils.JwtUtil;
import com.house.demo.common.utils.Md5Util;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xjj
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private HouseUserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request,HttpServletResponse response,HouseUser user) {

        if(request.getHeader("Token")!=null){
            if(jwtUtil.validateToken(request.getHeader("Token"),user)){
                return JSONObject.toJSONString(MyResult.fail("请勿重复登录"));
            }
        }

        String userName = user.getUserName();
        String secret = user.getUserPassword();
        HouseUser nuser = userMapper.getUserByName(userName);

        if (nuser != null) {
            String userPassword = nuser.getUserPassword();
            if (Md5Util.parseMD5(secret, userPassword)) {

                return JSONObject.toJSONString(MyResult.succ(jwtUtil.generateToken(nuser)));
            }
        }
        return JSONObject.toJSONString(MyResult.fail("登录失败"));
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){

        return "测试登录";
    }

//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }



}
