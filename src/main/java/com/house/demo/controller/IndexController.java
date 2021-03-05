package com.house.demo.controller;

import com.house.demo.service.HouseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private HouseOrderService orderService;

    @GetMapping("/lease/{page}")
    public String index(@PathVariable("page")int p){



        return null;
    }
}
