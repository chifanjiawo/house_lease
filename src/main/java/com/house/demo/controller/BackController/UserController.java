package com.house.demo.controller.BackController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.house.demo.common.response.MyResult;
import com.house.demo.common.utils.JwtUtil;
import com.house.demo.dao.HouseStarMapper;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.HouseUser;
import com.house.demo.service.HouseCommentService;
import com.house.demo.service.HouseOrderService;
import com.house.demo.service.HouseUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String userUpdate(HouseUser user) {
        int i = userService.updateUserById(user);
        if (i  != 0) {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改成功"));
        } else {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改失败"));
        }
    }

    @GetMapping("star/{id}")
    public String getMyStar(@PathVariable("id")int id ){

        List<HouseOrder> list = userService.getUserStar(id);

        return JSONObject.toJSONString(list);

    }

    @GetMapping("release/{id}")
    public String getMyRelease(@PathVariable("id")int id){




        return null;

    }

    @GetMapping("Comment/{id}")
    public String getMyComment(@PathVariable("id")int id){




        return null;

    }

    @GetMapping("myinfo/{id}")
    public String getMyInfo(@PathVariable("id")int id){

        HouseUser one = userService.getOne(
                new QueryWrapper<HouseUser>()
                        .eq("userId", id)

        );
        one.setUserPassword("");

        return JSONObject.toJSONString(one);
    }



    @PutMapping("updatepass")
    public String updatepass(HttpServletRequest request, String pass){

        String token = request.getHeader("token");

        int i = userService.updatePassWord(token,pass);


        if (i != 0) {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改成功"));
        } else {
            return JSONObject.toJSONString(MyResult.succ("用户信息修改失败"));
        }

    }




}