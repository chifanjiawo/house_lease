package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;

import com.house.demo.common.utils.JwtUtil;
import com.house.demo.common.utils.Md5Util;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.model.HouseUser;
import com.house.demo.service.HouseUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xjj
 */
@Slf4j
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private HouseUserService userService;

    @PostMapping("login")
    public String userLogin(HttpServletRequest request,HttpServletResponse response,HouseUser user) {
        String token = request.getHeader("token");
       return userService.tokenInspect(token, user,response);

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @RequiresAuthentication
    public String userLogout(HttpServletRequest request){
       return userService.logout(request.getHeader("token"));
    }

    @PostMapping("/register")
    public String userRegister(HouseUser user){
        int i = userService.register(user);
        if(i==1){
            return JSONObject.toJSONString(MyResult.succ("注册成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("注册失败"));
        }
    }




    }






//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }




