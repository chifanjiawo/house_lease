package com.house.demo.service;

import com.house.demo.model.HouseComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HouseCommentService extends IService<HouseComment>{

    List<HouseComment> getCommentPage(int current,int size);

    boolean createComment(HouseComment comment);

    boolean updateComment(HouseComment comment);

    boolean deleteComment(int id);

    List<HouseComment> getMyComment(int id,int current);


    List<HouseComment> getOrderComment(long id);

    List<HouseComment> getUserComment(int id);

    List<HouseComment> getMessageComment(int id);
}
