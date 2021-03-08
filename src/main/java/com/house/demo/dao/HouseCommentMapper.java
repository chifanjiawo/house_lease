package com.house.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.house.demo.model.HouseComment;

import java.util.List;

/**
 * @author xjj
 */
public interface HouseCommentMapper extends BaseMapper<HouseComment> {


    List<HouseComment> getMyComment(int id ,int current);
}