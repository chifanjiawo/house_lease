package com.house.demo.service;

import com.house.demo.model.HouseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.house.demo.model.vo.OrderVo;

import java.util.List;

public interface HouseOrderService extends IService<HouseOrder> {


    List<HouseOrder> getOrdersByPage(int current, int size);

    boolean createHouseOrder(HouseOrder order);

    boolean updateHouseOrder(HouseOrder order);

    boolean deleteHouseOrder(long id);

    HouseOrder selectSingleOrder(long id);

    List<HouseOrder> getMyRelease(int id, int current);

    List<OrderVo> getRecomOrder();

    OrderVo selectOrderById(Long id );
}


