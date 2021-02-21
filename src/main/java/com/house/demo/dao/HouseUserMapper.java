package com.house.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.demo.model.HouseUser;

/**
 * @author xjj
 */
public interface HouseUserMapper extends BaseMapper<HouseUser> {

    String getUserRoleByName(String username);

    String getPassWordByName(String username);
}