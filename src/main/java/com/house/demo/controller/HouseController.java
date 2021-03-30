package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.service.HouseOrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjj
 */
@RestController
@RequestMapping("house")
@RequiresAuthentication
public class HouseController {

    @Autowired
    private HouseOrderService orderService;


    @PostMapping("create")
    public String createHouseOrder(HouseOrder order){

        boolean b = orderService.createHouseOrder(order);

        if(b){
            return JSONObject.toJSONString(MyResult.succ("发布成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("发布失败"));
        }


    }

    @PutMapping("update")
    public String updateHouseOrder(HouseOrder order){


        boolean b = orderService.updateHouseOrder(order);
        if(b){
            return JSONObject.toJSONString(MyResult.succ("修改成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("修改失败"));
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteHouseOrder(@PathVariable("id")long id){

        boolean b = orderService.deleteHouseOrder(id);

        if(b){
            return JSONObject.toJSONString(MyResult.succ("删除成功"));
        }else {
            return JSONObject.toJSONString(MyResult.succ("删除失败"));
        }

    }

    @GetMapping("get/{id}")
    public String getHouseOrders(@PathVariable("id")long id){

        OrderVo order = orderService.selectOrderById(id);


        return JSONObject.toJSONString(order);
    }

    @GetMapping("getRecom")
    public MyResult getRecomOrder(){

        List<OrderVo> order = orderService.getRecomOrder();

        if(order!=null){
           return  MyResult.succ(order);
        }else {
            return MyResult.fail("查询失败");
        }

    }



}
