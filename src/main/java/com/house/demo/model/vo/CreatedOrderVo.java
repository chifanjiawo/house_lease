package com.house.demo.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xjj
 */
@Data
public class CreatedOrderVo implements Serializable {

    private int houseUserId;

    private String houseUserName;

    private List<String> furniture;
    private int houseArea;
    private String houseAddress;
    private int houseCost;
    private String houseDecoration;
    private String houseDesc;
    private Date houseEarlyTime;
    private String houseLeaseType;
    private Byte houseNum;
    private String houseOrientation;
    private String housePayment;
    private String houseTitle;
    private String houseType;
    private List<String> include;
    private List<String> requireP;
}
