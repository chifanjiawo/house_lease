package com.house.demo.controller;

import com.house.demo.common.response.MyResult;
import com.house.demo.dao.MessageMapper;
import com.house.demo.service.MessageService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjj
 */
@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("getMessage/{id}")
    public MyResult getMessage(@PathVariable("id")int id){

       int i = messageService.getMessageNum(id);

        if(i>0){

           return MyResult.succ(i);

        }else {
           return MyResult.fail("无最新消息");
        }
    }


    @DeleteMapping("delMessage/{id}")
    public MyResult delMessage(@PathVariable("id")int id){
        int i = messageService.delMessage(id);

        if(i>0){

          return   MyResult.succ(i);

        }else {
           return MyResult.fail("删除失败");
        }

    }

    @PutMapping("setRead/{id}")
    public MyResult setMessage(@PathVariable("id")int id){

        int i = messageService.setRead(id);

        if(i>0){
            return MyResult.succ("操作成功");
        }else {
            return  MyResult.fail("操作失败");

        }
    }
}
