package com.house.demo.controller;

import com.house.demo.common.response.MyResult;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.service.HouseOrderService;
import com.house.demo.utils.IPUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;
import java.util.List;

/**
 * @author xjj
 */
@RestController
@RequestMapping("base")
public class BaseController {

    @Autowired
    private HouseOrderService orderService;

    @GetMapping("get")
    public String getIp(HttpServletRequest request){

        String ipAddr = null;
        try {
            ipAddr = IPUtil.getIpAddr(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipAddr;

    }

    @GetMapping("searchByKey")
    public MyResult getResultByKey(@RequestParam("key") String keyWord){


        System.out.println(keyWord);
        List<OrderVo> list = orderService.searchByKeyWord(keyWord);

        if (list != null) {


            return MyResult.succ(list);
        }

        return MyResult.fail("查询失败");

    }



}
