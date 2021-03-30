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

    private int houseCost;

    private int houseArea;

    private String houseTitle;

    private String houseAddress;

    private String houseType;

    private String housePictureUrl;

    private String houseOrientation;

    private String houseDecoration;

    private String houseLeaseType;

     private List<String>  furniture;

     private String houseDesc;

     private String houseFurniture;


}
