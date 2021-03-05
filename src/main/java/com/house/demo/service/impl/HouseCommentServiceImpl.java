package com.house.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HouseCommentMapper commentMapper;


    @Override
    public List<HouseComment> getCommentPage(int current,int size) {

        IPage<HouseComment> page = new Page<>(current,size);

        commentMapper.selectPage(page,null);

        return page.getRecords();
    }

    @Override
    public boolean createComment(HouseComment comment) {
        int i = commentMapper.insert(comment);
        if(i==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateComment(HouseComment comment) {

        int i = commentMapper.updateById(comment);
        if(i==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteComment(int id) {
        int i = commentMapper.deleteById(id);
        if(i==1){
            return true;
        }else {
            return false;
        }
    }
}
