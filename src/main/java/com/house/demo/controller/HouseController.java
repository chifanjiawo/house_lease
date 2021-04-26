package com.house.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.house.demo.common.response.MyResult;
import com.house.demo.model.HouseOrder;
import com.house.demo.model.SearchParam;
import com.house.demo.model.vo.CreatedOrderVo;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.service.HouseOrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
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
    public String createHouseOrder(@RequestBody CreatedOrderVo order){
//        System.out.println(order);
       Long l = orderService.createHouseOrder(order);


        if(l!=null){

            return JSONObject.toJSONString(MyResult.succ(l));
        }else {
            return JSONObject.toJSONString(MyResult.fail("发布失败"));
        }



    }

    @PutMapping("update")
    public String updateHouseOrder(@RequestBody OrderVo order){




        boolean b = orderService.updateHouseOrder(order);
        if(b){
            return JSONObject.toJSONString(MyResult.succ("修改成功"));
        }else {
            return JSONObject.toJSONString(MyResult.fail("修改失败"));
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteHouseOrder(@PathVariable("id")long id){

        boolean b = false;
        try {
            b = orderService.deleteHouseOrder(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @GetMapping("get/listByFactor")
    public String getListByFactor(SearchParam searchParam){

        System.out.println(searchParam);
        List<OrderVo> listOrder = orderService.getListOrder(searchParam);

        return JSONObject.toJSONString(listOrder);
    }

    @GetMapping("getMyOrder")
    public MyResult getMyOrder(@RequestParam("userName")String name){


        List<OrderVo> order = orderService.getMyOrder(name);

        if(order!=null){

            return MyResult.succ(order);
        }else {
            return MyResult.fail("无所发布的订单");
        }
    }




}
