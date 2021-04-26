package com.house.demo.controller;

import com.house.demo.common.response.MyResult;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.service.HouseOrderService;
import com.house.demo.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjj
 */
@RestController
@RequestMapping("star")
public class StarController {

    @Autowired
    private HouseOrderService orderService;
    @Autowired
    private HouseUserService userService;

    @GetMapping("getStar")
    public MyResult getOrderStar(@RequestParam("houseId")long id, @RequestParam("userId")int userId){

        boolean b = orderService.getOrderStar(id, userId);

        if(b){
            return MyResult.succ("操作成功");
        }else {
            return MyResult.fail("操作失败");
        }

    }

    @PostMapping("inStar")

    public MyResult inStar(@RequestParam("houseId")long id, @RequestParam("userId")int userId){

        boolean b = orderService.inStar(id, userId);

        if(b){

            return MyResult.succ("操作成功");

        }
        return MyResult.fail("操作失败");
    }

    @DeleteMapping("delStar")
    public MyResult delStar(@RequestParam("houseId")long id, @RequestParam("userId")int userId){


        boolean b = orderService.delStar(id, userId);

        if(b){

            return MyResult.succ("操作成功");

        }
        return MyResult.fail("操作失败");
    }

    @GetMapping("getMyStar")
    public MyResult getMyStar(@RequestParam("userId")int userId){
        List<OrderVo> list = userService.getMyStarOrder(userId);

        if(list!=null){
            return MyResult.succ(list);
        }else {
            return MyResult.fail("查询失败");
        }
    }
}
