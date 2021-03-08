package com.house.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.demo.model.HouseOrder;import java.util.List;

/**
 * @author xjj
 */
public interface HouseOrderMapper extends BaseMapper<HouseOrder> {
    List<HouseOrder> getCurrentOrder();

    List<HouseOrder> getMyStarOrder(int id,int current);
}