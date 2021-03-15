package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;

import com.house.demo.model.HouseUser;
import com.house.demo.model.vo.RegisterInfoVo;
import com.house.demo.service.HouseUserService;
import lombok.extern.slf4j.Slf4j;
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

        System.out.println(user.getUserName()+":   "+user.getUserPassword());
        String token = request.getHeader("token");
       return userService.tokenInspect(token, user,response);

    }
    @PostMapping("tellogin")
    public MyResult tellogin(@RequestParam("iphonenum")String tel,@RequestParam("code")String code){


         return userService.loginByTel(tel,code);

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @RequiresAuthentication
    public String userLogout(HttpServletRequest request){
       return userService.logout(request.getHeader("token"));
    }

    @PostMapping("/register")
    public String userRegister(RegisterInfoVo reginfo){
        int i = userService.register(reginfo);

        if(i==1){
            return JSONObject.toJSONString(MyResult.succ("注册成功"));
        }else {
            return JSONObject.toJSONString(MyResult.fail("注册失败"));
        }
    }

    @PostMapping("sendMessage")
    public String sendMessage(@RequestParam("iphonenum")String iphonemun){
        return userService.sendMessage(iphonemun);

    }

    @GetMapping("test")
    public String test(){
        log.info("test");
        return JSONObject.toJSONString(MyResult.fail("test"));
    }

    }



//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }




