package com.house.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.model.HouseComment;
import com.house.demo.dao.HouseCommentMapper;
import com.house.demo.service.HouseCommentService;
/**
 * @author xjj
 */
@Service
public class HouseCommentServiceImpl extends ServiceImpl<HouseCommentMapper, HouseComment> implements HouseCommentService{

    @Override
    public List<HouseComment> getCommentPage() {

        return null;
    }

    @Override
    public boolean createComment() {
        return false;
    }

    @Override
    public boolean updateComment() {
        return false;
    }

    @Override
    public boolean deleteComment() {
        return false;
    }
}
