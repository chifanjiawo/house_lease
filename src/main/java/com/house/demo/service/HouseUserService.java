package com.house.demo.service;

import com.house.demo.model.HouseUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

public interface HouseUserService extends IService<HouseUser> {


    int register(HouseUser user);

    HouseUser getUserByName(String name);

    int updateUser(HouseUser user);

    String gentoken(HouseUser user);

    String tokenInspect(String token, HouseUser user, HttpServletResponse response);

    String logout(String token);




}





