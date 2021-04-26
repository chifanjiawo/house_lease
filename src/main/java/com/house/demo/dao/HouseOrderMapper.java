package com.house.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.SearchParam;
import com.house.demo.model.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xjj
 */
public interface HouseOrderMapper extends BaseMapper<HouseOrder> {
//    int deleteByPrimaryKey(Long houseId);
//
//    int insert(HouseOrder record);
//
//    int insertSelective(HouseOrder record);
//
//    HouseOrder selectByPrimaryKey(Long houseId);
//
//    int updateByPrimaryKeySelective(HouseOrder record);
//
//    int updateByPrimaryKey(HouseOrder record);

    List<HouseOrder> getCurrentOrder();

    List<HouseOrder> getMyStarOrder(int id, int current);

    List<OrderVo> getRecomOrder();

    OrderVo getOrderById(Long id);

    List<OrderVo> getListByFactor(SearchParam searchParam);

    List<OrderVo> getMyOrder(int id);

    int setUrl(@Param("id") Long id,@Param("urls")String urls);

    int delUrl(Long id);

    List<String> getUrls(Long id);

    List<OrderVo> searchByKeyWord(@Param("key")String keyWord);


}