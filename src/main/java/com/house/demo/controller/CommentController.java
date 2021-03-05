package com.house.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author xjj
 */
@RestController()
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/createcom{id}")
    public String createCom(@PathVariable("id")int id){
        return null;
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
