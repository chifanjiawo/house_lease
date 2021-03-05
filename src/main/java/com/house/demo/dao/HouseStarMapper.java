package com.house.demo.dao;

import com.house.demo.model.HouseOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xjj
 */

public interface HouseStarMapper {

    @Select("select * from house_order where house_user_id = #{id} ;")
    List<HouseOrder> getStarOrders(int id);


}
