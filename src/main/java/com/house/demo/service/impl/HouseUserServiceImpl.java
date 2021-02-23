package com.house.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.model.HouseUser;
import com.house.demo.dao.HouseUserMapper;
import com.house.demo.service.HouseUserService;

@Service
public class HouseUserServiceImpl extends ServiceImpl<HouseUserMapper, HouseUser> implements HouseUserService {

}





