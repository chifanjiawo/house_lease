package com.house.demo.service;

import com.house.demo.model.HouseComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HouseCommentService extends IService<HouseComment>{

    List<HouseComment> getCommentPage(int current,int size);

    boolean createComment(HouseComment comment);

    boolean updateComment(HouseComment comment);

    boolean deleteComment(int id);



}
