package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.model.HouseComment;
import com.house.demo.service.HouseCommentService;
import com.house.demo.utils.MessageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjj
 */
@RestController()
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private HouseCommentService commentService;


    @PostMapping("/createcom")
    public String createCom(@RequestBody HouseComment comment){

        System.out.println(comment);
        boolean b = commentService.createComment(comment);
        if(b){
            return JSONObject.toJSONString(MyResult.succ("创建成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("创建成功"));
        }

    }

//    @GetMapping("/getcom/{id}")
//    public String getCom(@PathVariable("id")int id ){
//
//        HouseComment comment = commentService.getById(id);
//        return JSONObject.toJSONString(comment);
//
//    }

    @GetMapping("getCommentByOrder/{id}")
    public MyResult getCommentOrder(@PathVariable("id")long id){


        List<HouseComment> comments = commentService.getOrderComment(id);

        if(comments!=null){
            return MyResult.succ(comments);

        }
        return MyResult.fail("查询失败");
    }


    @PutMapping("/updatecom")
    public String updateCom(HouseComment comment){

        boolean b = commentService.updateComment(comment);
        if(b){
            return JSONObject.toJSONString(MyResult.succ("修改成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("修改成功"));
        }


    }

    @DeleteMapping("/deleteComment/{id}")
    public String deleteCom(@PathVariable("id")int id){

        boolean b = commentService.deleteComment(id);

        if(b){
            return JSONObject.toJSONString(MyResult.succ("删除成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("删除成功"));
        }

    }

    @GetMapping("getUserComment/{id}")
    public MyResult getUserComment(@PathVariable("id")int id ){

        List<HouseComment> list = commentService.getUserComment(id);
        if(list!=null){
            return MyResult.succ(list);
        }
        return MyResult.fail("获取失败");
    }

    @GetMapping("getReplay/{id}")
    public  MyResult getReplay(@PathVariable("id")int id ){

        List<HouseComment> list = commentService.getMessageComment(id);

        if(list!=null){

            return  MyResult.succ(list);
        }else {

            return MyResult.fail("操作失败");

        }


    }


}
