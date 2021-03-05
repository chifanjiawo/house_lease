package com.house.demo.service;

import com.house.demo.model.HouseComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HouseCommentService extends IService<HouseComment>{


    List<HouseComment> getCommentPage();

    boolean createComment();

    boolean updateComment();

    boolean deleteComment();



}
