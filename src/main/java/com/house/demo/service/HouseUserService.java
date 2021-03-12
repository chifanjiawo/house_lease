package com.house.demo.service;

import com.house.demo.model.HouseOrder;
import com.house.demo.model.HouseUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.house.demo.model.vo.RegisterInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HouseUserService extends IService<HouseUser> {


    int register(RegisterInfoVo infoVo);

    HouseUser getUserByName(String name);

    int updateUserById(HouseUser user);

    int updateUserByName(HouseUser user);

    int updatePassWord(String name, String pass);

    String gentoken(HouseUser user);

    String tokenInspect(String token, HouseUser user, HttpServletResponse response);

    String logout(String token);


    String getCurrentUserName(String token);

    List<HouseOrder> getUserStar(int userId);

    String sendMessage(String iphoneNum);


}






