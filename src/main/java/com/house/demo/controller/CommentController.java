package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.MyResult;
import com.house.demo.model.HouseComment;
import com.house.demo.service.HouseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xjj
 */
@RestController()
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private HouseCommentService commentService;

    @PostMapping("/createcom")
    public String createCom(HouseComment comment){

        boolean b = commentService.createComment(comment);
        if(b){
            return JSONObject.toJSONString(MyResult.succ("创建成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("创建成功"));
        }

    }

    @GetMapping("/getcom/{id}")
    public String getCom(@PathVariable("id")int id ){

        HouseComment comment = commentService.getById(id);
        return JSONObject.toJSONString(comment);

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

    @DeleteMapping("/deletecom/{id}")
    public String deleteCom(@PathVariable("id")int id){

        boolean b = commentService.deleteComment(id);

        if(b){
            return JSONObject.toJSONString(MyResult.succ("删除成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("删除成功"));
        }

    }



}
