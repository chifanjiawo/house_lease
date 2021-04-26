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
    public MyResult userLogin(HouseUser user) {
        System.out.println(user.getUserName()+":   "+user.getUserPassword());

        String res = userService.gentoken(user);

        if(res!=null){
            return MyResult.succ((Object)res);
        }
       return MyResult.fail("操作失败");
    }

    @GetMapping("inspectToken")
    public MyResult inspect(HttpServletRequest request,@RequestParam("userName") String name){
        String token = request.getHeader("token");

        if (userService.tokenInspect(token,name)) {
            return MyResult.succ("token有效");
        }


        return MyResult.fail("token失效");
    }


    @PostMapping("tellogin")
    public MyResult tellogin(@RequestParam("iphonenum")String tel,@RequestParam("code")String code){

         return userService.loginByTel(tel,code);

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
//    @RequiresAuthentication
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

    @GetMapping("getUserId")
    public MyResult getID(@RequestParam("userName")String name){

        HouseUser user = userService.getUserByName(name);
        if(user!=null){
            return MyResult.succ(user.getUserId());
        }else {
            return MyResult.fail("查询失败");
        }

    }


}



//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    public String userInfo(){
//
//    }




