package com.house.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.house.demo.common.response.MyResult;
import com.house.demo.model.HouseComment;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.HouseUser;
import com.house.demo.model.vo.UserVo;
import com.house.demo.service.HouseCommentService;
import com.house.demo.service.HouseOrderService;
import com.house.demo.service.HouseUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author xjj
 */
@RestController
@RequestMapping("userdata")
@RequiresAuthentication
public class UserController {

    @Autowired
    private  HouseUserService userService;

    @Autowired
    private HouseOrderService orderService;

    @Autowired
    private HouseCommentService commentService;


    @PutMapping("update")
    public String userUpdate(@RequestBody HouseUser user) {
        System.out.println(user.getUserId());
        int i = userService.updateUserById(user);
        if (i  != 0) {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改成功"));
        } else {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改失败"));
        }
    }

//    @GetMapping("star/{id}")
//    public String getMyStar(@PathVariable("id")int id ){
//
//        List<HouseOrder> list = userService.getUserStar(id);
//
//        return JSONObject.toJSONString(list);
//
//    }

    @GetMapping("release/{current}")
    public String getMyRelease(@PathVariable("current")int current,@RequestParam("userId") int id){

        List<HouseOrder> list = orderService.getMyRelease(id, current);

        return JSONObject.toJSONString(list);

    }

    @GetMapping("Comment/{current}")
    public String getMyComment(@PathVariable("current")int current,@RequestParam("useId")int id ){
        List<HouseComment> myComment = commentService.getMyComment(id,current);

        return JSONObject.toJSONString(myComment);

    }

    @GetMapping("myinfo")
    public HouseUser getMyInfo(@RequestParam("userName") String name){

        HouseUser one = userService.getUserByName(name);
        one.setUserPassword(null);

        return one;
    }



    @PutMapping("updatepass")
    public String updatepass(HttpServletRequest request,@RequestParam("oldPass")String oldPass,@RequestParam("newPass")String newPass){

        String token = request.getHeader("token");

        int i = userService.updatePassWord(token,oldPass,newPass);

        if (i != 0) {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改成功"));
        } else {
            return JSONObject.toJSONString(MyResult.fail("用户信息修改失败"));
        }

    }

    @GetMapping("getUser/{id}")
    public String getUser(@PathVariable("id")String id){

        UserVo user = userService.getUserById(id);

        if(user!=null){

            return JSONObject.toJSONString(MyResult.succ(user));
        }else {
            return JSONObject.toJSONString(MyResult.fail("查询失败"));

        }
    }







}
