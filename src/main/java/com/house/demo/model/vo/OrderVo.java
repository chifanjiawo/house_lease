package com.house.demo.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xjj
 */
@Data
public class OrderVo implements Serializable {

    private Long houseId;

    private String userName;

    private Date userLastLoginTime;

    private String userTel;

    private String userNickName;

    private int houseUserId;

    private int houseCost;

    private int houseArea;

    private String houseTitle;

    private String houseAddress;

    private String addressDetail;

    private String addressZone;

    private String houseType;

    private String housePictureUrl;

    private String houseOrientation;

    private String houseDecoration;

    private String houseLeaseType;

     private List<String>  furniture;

     private String houseDesc;

     private String houseFurniture;

     private Date houseReleaseTime;

    private List<String> include;

    private String houseRentInclude;

    private String houseRequire;

    private List<String> requireP;

    private String housePayment;

    private Date houseEarlyTime;

    private List<String> images;

    private List<String> furnitureInCn;

    private  int houseNum;

    private String userAvatar;



}
