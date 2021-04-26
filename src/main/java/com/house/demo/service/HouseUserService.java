package com.house.demo.service;

import com.house.demo.common.response.MyResult;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.HouseUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.model.vo.RegisterInfoVo;
import com.house.demo.model.vo.UserVo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HouseUserService extends IService<HouseUser> {


    int register(RegisterInfoVo infoVo);

    HouseUser getUserByName(String name);

    int updateUserById(HouseUser user);

    int updateUserByName(HouseUser user);

    int updatePassWord(String name, String oldPass,String newPass);

    String gentoken(HouseUser user);

    boolean tokenInspect(String token, String name);

    String logout(String token);


    String getCurrentUserName(String token);

    List<HouseOrder> getUserStar(int userId);

    String sendMessage(String iphoneNum);

    MyResult loginByTel(String tel, String code);

    String genToeknByTel(HouseUser user);

    UserVo getUserById(String id);


    List<OrderVo> getMyStarOrder(int userId);


    Integer setUserAvatar( int id,String avatarUrl);
}







