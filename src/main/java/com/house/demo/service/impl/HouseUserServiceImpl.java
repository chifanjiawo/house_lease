package com.house.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.common.utils.JwtUtil;
import com.house.demo.common.utils.Md5Util;
import com.house.demo.dao.HouseStarMapper;
import com.house.demo.model.HouseOrder;
import com.house.demo.shiro.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.model.HouseUser;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.service.HouseUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = "userCache")
public class HouseUserServiceImpl extends ServiceImpl<HouseUserMapper, HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserMapper userMapper;

    @Autowired
    private HouseStarMapper starMapper;


    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public int register(HouseUser user) {


        user.setUserRegisterTime(new Date());
        user.setUserBanStatus((byte) 0);
        String nPass = Md5Util.encodeByMD5(user.getUserPassword());
        user.setUserPassword(nPass);
        user.setUserRole("user");

        int i = userMapper.insert(user);
        return i;

    }

    @Cacheable(key = "#p0")
    @Override
    public HouseUser getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int updateUserById(HouseUser user) {
        return userMapper.updateById(user);
    }

    @Override
    public int updateUserByName(HouseUser user) {

        return userMapper.updateUserByName(user);
    }

    @Override
    public int updatePassWord(String token, String pass) {

        String name = getCurrentUserName(token);

        String sf = Md5Util.encodeFirst(pass);

        if (name != null) {
            if (Md5Util.parseMD5(sf, userMapper.getUserByName(name).getUserPassword())) {
                return userMapper.updatePassWord(name, pass);
            }

        }
        return 0;

    }


    @Override
    public String gentoken(HouseUser user) {

        String userName = user.getUserName();
        String secret = user.getUserPassword();
        HouseUser nuser = userMapper.getUserByName(userName);

        if (nuser != null) {
            String userPassword = nuser.getUserPassword();
            if (Md5Util.parseMD5(secret, userPassword)) {
                userMapper.updateLoginTime(new Date(), nuser.getUserName());
                return jwtUtil.generateToken(nuser);
            }
        }
        return null;

    }

    @Override
    public String tokenInspect(String token, HouseUser user, HttpServletResponse response) {


        String msg = "";

        if (token != null) {
            if (jwtUtil.validateToken(token, user)) {
                if (jwtUtil.getBlockedToken(token)) {
                    msg = "token已经过期";
                } else {
                    msg = "请不要重复登录";
                }
            }
            return JSONObject.toJSONString(MyResult.fail(msg));

        } else {
            if (user == null) {
                return JSONObject.toJSONString(MyResult.fail("请输入用户名密码"));
            }
            String t = gentoken(user);

            if (t != null) {
                response.setHeader("token", t);
                return JSONObject.toJSONString(MyResult.succ("登录成功"));
            }
            return JSONObject.toJSONString(MyResult.fail("登录失败"));

        }

    }

    @Override
    public String logout(String token) {

        jwtUtil.setTokenBlock(token);
        SecurityUtils.getSubject().logout();
        return JSONObject.toJSONString(MyResult.succ("注销成功"));

    }

    @Override
    public String getCurrentUserName(String token) {

        return jwtUtil.getUserNameFromToken(token);
    }


    @Override
    public List<HouseOrder> getUserStar(int userId) {

        return starMapper.getStarOrders(userId);
    }
}






