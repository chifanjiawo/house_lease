package com.house.demo.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.dao.HouseOrderMapper;
import com.house.demo.model.HouseOrder;
import com.house.demo.service.HouseOrderService;

/**
 * @author xjj
 */
@Service
public class HouseOrderServiceImpl extends ServiceImpl<HouseOrderMapper, HouseOrder> implements HouseOrderService {


    @Autowired
    private HouseOrderMapper orderMapper;


    @Autowired
    private IdWorker idWorker;

    @Override
    public List<HouseOrder> getOrdersByPage(int current, int size) {

        IPage<HouseOrder> page = new Page<>(current, size);
        orderMapper.selectPage(page, null);

        return page.getRecords();


    }


    @Override
    public boolean createHouseOrder(HouseOrder order) {

        order.setHouseId(idWorker.nextId());

        order.setHouseReleaseTime(new Date());

        int i = orderMapper.insert(order);

        if (i == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updateHouseOrder(HouseOrder order) {

        if (orderMapper.updateById(order) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteHouseOrder(long id) {

        if (orderMapper.deleteById(id) == 1) {
            return true;
        }
        return false;
    }


    @Override
    public HouseOrder selectSingleOrder(long id) {

        return orderMapper.selectById(id);
    }

    @Override
    public List<HouseOrder> getMyRelease(int id, int current) {


        List<HouseOrder> list = orderMapper.getMyStarOrder(id, current);

        return list;
    }

    @Override
    public List<OrderVo> getRecomOrder() {

        List<OrderVo> list = orderMapper.getRecomOrder();

        return list;

    }

    @Override
    public OrderVo selectOrderById(Long id) {

        OrderVo order = orderMapper.getOrderById(id);
//        OrderVo orderVo = new OrderVo();
//        BeanUtils.copyProperties(order,orderVo);

        String temp = order.getHouseFurniture();


        String[] strings = temp.split(",");
        List<String> list  =  new ArrayList<>();


        for (int i = 0; i < strings.length; i++) {

            list.add(strings[i]);
        }

        order.setFurniture(list);

        return order;
    }


}


