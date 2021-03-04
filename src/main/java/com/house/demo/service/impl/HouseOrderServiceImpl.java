package com.house.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.dao.HouseOrderMapper;
import com.house.demo.model.HouseOrder;
import com.house.demo.service.HouseOrderService;
@Service
public class HouseOrderServiceImpl extends ServiceImpl<HouseOrderMapper, HouseOrder> implements HouseOrderService{



}
