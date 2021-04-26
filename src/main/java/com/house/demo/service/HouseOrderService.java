package com.house.demo.service;

import cn.hutool.db.sql.Order;
import com.house.demo.model.HouseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.house.demo.model.SearchParam;
import com.house.demo.model.vo.CreatedOrderVo;
import com.house.demo.model.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

public interface HouseOrderService extends IService<HouseOrder> {


    List<HouseOrder> getOrdersByPage(int current, int size);

    Long createHouseOrder(CreatedOrderVo order);

    boolean updateHouseOrder(OrderVo order);

    boolean deleteHouseOrder(long id) throws IOException;

    HouseOrder selectSingleOrder(long id);

    List<HouseOrder> getMyRelease(int id, int current);

    List<OrderVo> getRecomOrder();

    OrderVo selectOrderById(Long id );

    List<OrderVo> getListOrder(SearchParam searchParam);

    List<OrderVo> getMyOrder(String name);

    Integer setUrl(Long id,String url);


    boolean getOrderStar(long id,int userId);

    boolean inStar(long id,int  userId);

    boolean delStar(long id ,int userId);

    List<OrderVo> searchByKeyWord( String keyWord);

}


