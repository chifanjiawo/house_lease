package com.house.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author xjj
 */
@RestController()
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/createCom")
    public String createCom(){


        return null;
    }

    @GetMapping("/getCom/{id}")
    public String getCom(@PathVariable("id")int id ){


        return null;

    }

    @PutMapping("/updateCom/{id}")
    public String updateCom(@PathVariable("id")int id){

        return null;


    }

    @DeleteMapping("/deleteCom/{id}")
    public String deleteCom(@PathVariable("id")int id){

        return null;

    }



}
