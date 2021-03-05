package com.house.demo.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.house.demo.common.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public HouseOrder selectSingleOrder(long i) {

        return orderMapper.selectById(i);
    }
}

