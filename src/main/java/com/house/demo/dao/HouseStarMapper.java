package com.house.demo.dao;

import com.house.demo.model.HouseOrder;
import com.house.demo.model.vo.OrderVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xjj
 */

public interface HouseStarMapper {

    @Select("select * from house_order where house_id in(" +
            "select star_house_id as house_id from house_star_ref where star_user_id = #{id} " +
            "); ")
    List<OrderVo> getStarOrders(int id);

    @Select("select * from house_star_ref where star_house_id = #{houseId} and star_user_id =#{userId} ")
    Long getOrderStar(@Param("houseId")long id,@Param("userId")int userId);

    @Insert("insert into house_star_ref values(#{houseId},#{userId} )")
    int inStar(@Param("houseId")long id,@Param("userId")int  userId);

    @Delete("delete from house_star_ref where star_house_id = #{houseId} and star_user_id = #{userId} ")
    int delStar(@Param("houseId")long id,@Param("userId")int  userId);

    @Delete("delete from house_star_ref where star_house_id = #{id} ")
    int clearStar(long id);


}
