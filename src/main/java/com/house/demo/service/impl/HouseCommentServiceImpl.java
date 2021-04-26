package com.house.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.house.demo.dao.HouseOrderMapper;
import com.house.demo.dao.MessageMapper;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.service.HouseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.model.HouseComment;
import com.house.demo.dao.HouseCommentMapper;
import com.house.demo.service.HouseCommentService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjj
 */
@Service
public class HouseCommentServiceImpl extends ServiceImpl<HouseCommentMapper, HouseComment> implements HouseCommentService{

    @Autowired
    private HouseCommentMapper commentMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private HouseOrderMapper orderMapper;


    @Override
    public List<HouseComment> getCommentPage(int current,int size) {

        IPage<HouseComment> page = new Page<>(current,size);

        commentMapper.selectPage(page,null);

        return page.getRecords();
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createComment(HouseComment comment) {

        comment.setCommentCreateTime(new Date());
        int i = commentMapper.insert(comment);


        if(i==1){
            if (comment.getCommentPid()!=0) {

                HouseComment comment1 = commentMapper.selectById(comment.getCommentPid());
                messageMapper.insertMessage(comment.getCommentAuthorId(),comment1.getCommentAuthorId(),comment.getCommentId());

            }else {
                OrderVo order = orderMapper.getOrderById(comment.getCommentHouseId());

                if(comment.getCommentAuthorId()!=order.getHouseUserId()) {

                    messageMapper.insertMessage(comment.getCommentAuthorId(), order.getHouseUserId(),comment.getCommentId());
                }
            }
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteComment(int id) {
        int i = commentMapper.deleteById(id);



        if(i==1){

            if( messageMapper.delMessageById(id)>0){
                return true;
            }

        }
            return false;

    }

    @Override
    public List<HouseComment> getMyComment(int id,int current) {

        List<HouseComment> myComment = commentMapper.getMyComment(id, current);

        return myComment;
    }

    @Override
    public List<HouseComment> getOrderComment(long id) {


        List<HouseComment> list = commentMapper.getOrderComment(id);

        List<HouseComment> temp = new LinkedList<>(list);

        Map<Integer,Integer> map =  new HashMap<>();

        for (int i = 0; i < temp.size(); i++) {

            HouseComment comment = temp.get(i);

            if (comment.getCommentPid()==0) {
                map.put(comment.getCommentId(),i+1);
                continue;
            }
            int index = map.get(comment.getCommentPid());

            temp.add(index,comment);
            temp.remove(i+1);
            map.put(comment.getCommentPid(),++index);
            map.put(comment.getCommentId(),index);

        }

        return temp;
    }

    @Override
    public List<HouseComment> getUserComment(int id) {


        return commentMapper.getUserComment(id);
    }

    @Override
    public List<HouseComment> getMessageComment(int id) {


        List<HouseComment> list = messageMapper.getCommentList(id);

        return list;

    }


}
