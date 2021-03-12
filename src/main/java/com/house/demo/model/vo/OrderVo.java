package com.house.demo.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xjj
 */
@Data
public class OrderVo implements Serializable {

    private String userName;

    private int houseCost;

    private int houseArea;

    private String title;

    private String houseAddress;

    private String houseType;
}
