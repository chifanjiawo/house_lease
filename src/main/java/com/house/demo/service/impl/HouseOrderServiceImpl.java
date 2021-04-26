package com.house.demo.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.house.demo.dao.*;
import com.house.demo.model.HouseComment;
import com.house.demo.model.HouseUser;
import com.house.demo.model.SearchParam;
import com.house.demo.model.vo.CreatedOrderVo;
import com.house.demo.model.vo.OrderVo;
import com.house.demo.utils.FileUploadUtil;
import com.house.demo.utils.IdWorker;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.house.demo.model.HouseOrder;
import com.house.demo.service.HouseOrderService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjj
 */
@Service
public class HouseOrderServiceImpl extends ServiceImpl<HouseOrderMapper, HouseOrder> implements HouseOrderService {


    @Autowired
    private HouseOrderMapper orderMapper;


    @Autowired
    private HouseUserMapper userMapper;

    @Autowired
    private HouseStarMapper starMapper;

    @Autowired
    private HouseCommentMapper commentMapper;

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<HouseOrder> getOrdersByPage(int current, int size) {

        IPage<HouseOrder> page = new Page<>(current, size);
        orderMapper.selectPage(page, null);

        return page.getRecords();


    }


    @Override
    public Long createHouseOrder(CreatedOrderVo order) {

        HouseOrder norder = new HouseOrder();

        List<String> furniture = order.getFurniture();
        StringBuilder sbFuniture = new StringBuilder();


        String[] s = {"床","衣柜","沙发","宽带","油烟机","洗衣机","冰箱","暖气","阳台","可做饭","燃气灶","智能门锁","空调","厕所","电视","热水器"};

        int j=0;

        for (int i = 0; i < s.length; i++) {


//                if (j+1<furniture.size() && s[i].equals(furniture.get(j))) {
//                    sbFuniture.append(1);
//                    j++;
//                    if(i+1<s.length){
//                        sbFuniture.append(",");
//                    }
//                    continue;
//                }
//
//            sbFuniture.append(0);
//            if(i+1<s.length){
//                    sbFuniture.append(",");
//            }

            if (furniture.contains(s[i])) {
                sbFuniture.append(1);
            }else {
                sbFuniture.append(0);
            }


            if(i+1<s.length){
                sbFuniture.append(",");
            }




        }
        norder.setHouseFurniture(sbFuniture.toString());
        //

        List<String> include = order.getInclude();

        StringBuilder sbInclude = new StringBuilder();

        getSbString(include, sbInclude);

        norder.setHouseRentInclude(sbInclude.toString());
        //

        List<String> requireP = order.getRequireP();

        StringBuilder sbRequire = new StringBuilder();



        getSbString(requireP,sbRequire);


        norder.setHouseRequire(sbRequire.toString());

        SimpleDateFormat sdf = new SimpleDateFormat();


        BeanUtils.copyProperties(order,norder);

        Long orderId = idWorker.nextId();

        norder.setHouseId(orderId);
//
        norder.setHouseReleaseTime(new Date());

        String name = order.getHouseUserName();

        HouseUser userByName = userMapper.getUserByName(name);

        norder.setHouseUserId(userByName.getUserId());




        int i = orderMapper.insert(norder);

        if (i == 1) {
            return orderId;
        }

        return null;
    }

    private void getSbString(List<String> list, StringBuilder sb) {
        if (list !=null){
            Iterator<String> includeIterator = list.iterator();

            while(includeIterator.hasNext()){
                sb.append(includeIterator.next());
                if(includeIterator.hasNext()){
                    sb.append(",");
                }
            }

        }
    }

    @Override
    public boolean updateHouseOrder(OrderVo order) {


        HouseOrder order1 = new HouseOrder();

        BeanUtils.copyProperties(order,order1);

        String[] s = {"床","衣柜","沙发","宽带","油烟机","洗衣机","冰箱","暖气","阳台","可做饭","燃气灶","智能门锁","空调","厕所","电视","热水器"};

        List<String> cn = order.getFurnitureInCn();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            if (cn.contains(s[i])) {
                sb.append(1);
            }else {
                sb.append(0);

            }
            if(i+1<s.length){
                sb.append(",");
            }
        }

        order1.setHouseFurniture(sb.toString());


        List<String> include = order.getInclude();

        StringBuilder sbInclude = new StringBuilder();

        for (int k = 0; k < include.size(); k++) {

            sbInclude.append(include.get(k));
            if(k+1<include.size()){
                sbInclude.append(",");
            }

        }

        order1.setHouseRequire(sbInclude.toString());

        List<String> requireP = order.getRequireP();


        StringBuilder sbInRequire = new StringBuilder();

        for (int k = 0; k < requireP.size(); k++) {
            sbInRequire.append(requireP.get(k));

            if(k+1<requireP.size()){
                sbInRequire.append(",");

            }

        }

        order1.setHouseRequire(sbInRequire.toString());


        if (orderMapper.updateById(order1)==1) {
            return true;
        }


        return false;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteHouseOrder(long id) throws IOException {

        if (orderMapper.deleteById(id) == 1) {
            orderMapper.delUrl(id);
            commentMapper.delOrderComment(id);
            starMapper.clearStar(id);
            FileUploadUtil.deleteFile(String.valueOf(id));

            return true;
        }

        return false;
    }


    @Override
    public HouseOrder selectSingleOrder(long id) {

        return orderMapper.selectById(id);
    }

    @Override
    public List<HouseOrder> getMyRelease(int id, int current) {


        List<HouseOrder> list = orderMapper.getMyStarOrder(id, current);

        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<OrderVo> getRecomOrder() {

        List<OrderVo> list = orderMapper.getRecomOrder();

        for (int i = 0; i < list.size(); i++) {

            OrderVo vo = list.get(i);
            List<String> urls = orderMapper.getUrls(vo.getHouseId());

            if(urls!=null){
                vo.setImages(urls);
            }


        }

        return list;

    }

    @Override
    public OrderVo selectOrderById(Long id) {

        OrderVo order = orderMapper.getOrderById(id);

        List<String> urls = orderMapper.getUrls(id);

        order.setImages(urls);
//        OrderVo orderVo = new OrderVo();
//        BeanUtils.copyProperties(order,orderVo);

        String temp = order.getHouseFurniture();


        String[] strings = temp.split(",");
        List<String> list  =  new ArrayList<>();


        for (int i = 0; i < strings.length; i++) {

            list.add(strings[i]);
        }

        order.setFurniture(list);

        String s = order.getHouseAddress();

        String regex = "\\([\\S\\s]*\\)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            String group = matcher.group();
            order.setAddressDetail( group.substring(1,group.length()-1));
            order.setAddressZone(s.substring(0,s.indexOf("(")));
        }

        String houseRentInclude = order.getHouseRentInclude();

        String[] split = houseRentInclude.split(",");

        order.setInclude(Arrays.asList(split));

        String houseRequire = order.getHouseRequire();

        String[] split1 = houseRequire.split(",");

        order.setRequireP(Arrays.asList(split1));

        String[] furn = order.getHouseFurniture().split(",");



        String[] f = {"床","衣柜","沙发","宽带","油烟机","洗衣机","冰箱","暖气","阳台","可做饭","燃气灶","智能门锁","空调","厕所","电视","热水器"};

        List<String > furniture = new LinkedList<>();

        for (int i = 0; i < f.length; i++) {
            if("0".equals(furn[i])){
                continue;
            }if("1".equals(furn[i])) {
                furniture.add(f[i]);
            }
        }
        order.setFurnitureInCn(furniture);



        return order;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<OrderVo> getListOrder(SearchParam searchParam) {

//        String[] address = {};
//        String[] cost = {"不限","500以下",""};
//        String[] type = {"不限","一室","两室","三室","四室","四室以上"} ;
//
//        if (searchParam.getHouseAddress()!=null) {
//            int i = Integer.parseInt(searchParam.getHouseAddress());
//            searchParam.setHouseAddress(address[i]);
//        }
//        if (searchParam.getHouseCost()!=null) {
//            int i = Integer.parseInt(searchParam.getHouseAddress());
//            searchParam.setHouseCost(address[i]);
//        }

        List<OrderVo> list = orderMapper.getListByFactor(searchParam);

        for (int i = 0; i < list.size(); i++) {

            OrderVo orderVo = list.get(i);

            List<String> urls = orderMapper.getUrls(orderVo.getHouseId());
            if(urls!=null){
                orderVo.setImages(urls);
            }
        }

        return list;
    }

    @Override
    public List<OrderVo> getMyOrder(String name) {

        HouseUser user = userMapper.getUserByName(name);
        if(user!=null){

            List<OrderVo> list = orderMapper.getMyOrder(user.getUserId());

//            for (OrderVo o :
//                    list) {
//
//
//
//            }

            return list;
        }
        return null;


    }

    @Override
    public Integer setUrl(Long id,String url) {

        int i = orderMapper.setUrl(id, url);
        return i;

    }

    @Override
    public boolean getOrderStar(long id, int userId) {

        Long i = starMapper.getOrderStar(id, userId);

        if(i==null){
            return false;
        }else {
            return true;
        }


    }

    @Override
    public boolean inStar(long id, int  userId) {

        int i = starMapper.inStar(id, userId);

        if(i==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delStar(long id, int userId) {
        int i = starMapper.delStar(id, userId);

        if(i==0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<OrderVo> searchByKeyWord(String keyWord) {

        System.out.println(keyWord);
        List<OrderVo> list = orderMapper.searchByKeyWord(keyWord);

        Iterator<OrderVo> iterator = list.iterator();

        while (iterator.hasNext()) {

            OrderVo next = iterator.next();
            next.setImages(orderMapper.getUrls(next.getHouseId()));

        }
        return list;
    }


}


