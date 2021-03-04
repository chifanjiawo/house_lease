package com.house.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author xjj
 */
@RestController
@RequestMapping("house")
public class HouseController {

    @PostMapping("create")
    public String createHouseOrder(){
        return null;
    }

    @PutMapping("update")
    public String updateHouseOrder(){
        return null;
    }

    @DeleteMapping("delete")
    public String deleteHouseOrder(){
        return null;
    }

    @GetMapping("get")
    public String getHOuseOrders(){
        return null;
    }


}
