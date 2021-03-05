package com.house.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.demo.model.HouseUser;import org.apache.ibatis.annotations.Param;import java.util.Date;

/**
 * @author xjj
 */
public interface HouseUserMapper extends BaseMapper<HouseUser> {
    HouseUser getUserByName(String name);

    int updateUserByName(HouseUser user);

    int updatePassWord(@Param("name") String name, @Param("pass") String pass);

    int updateLoginTime(@Param("dateTime") Date dateTime, @Param("name") String name);
}