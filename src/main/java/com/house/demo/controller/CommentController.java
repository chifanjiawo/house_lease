package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
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

        return null;

    }

    @PutMapping("/updatecom/{id}")
    public String updateCom(@PathVariable("id")int id){
        return null;

    }

    @DeleteMapping("/deletecom/{id}")
    public String deleteCom(@PathVariable("id")int id){

        return null;

    }



}
