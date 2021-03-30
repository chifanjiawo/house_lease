package com.house.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * @author xjj
 */
@Data
@TableName(value = "house_order")
public class HouseOrder {
    private static final long serialVersionUID = 8221967145116380824L;
    @TableId(value = "house_id", type = IdType.INPUT)
    private Long houseId;

    @TableField(value = "house_user_id")
    private Integer houseUserId;

    @TableField(value = "house_type")
    private String houseType;

    @TableField(value = "house_orientation")
    private String houseOrientation;

    @TableField(value = "house_area")
    private Integer houseArea;

    @TableField(value = "house_cost")
    private Integer houseCost;

    @TableField(value = "house_payment")
    private String housePayment;

    @TableField(value = "house_rent_include")
    private String houseRentInclude;

    @TableField(value = "house_early_time")
    private Date houseEarlyTime;

    @TableField(value = "house_num")
    private Byte houseNum;

    @TableField(value = "house_decoration")
    private String houseDecoration;

    @TableField(value = "house_lease_type")
    private String houseLeaseType;

    @TableField(value = "house_address")
    private String houseAddress;

    @TableField(value = "house_furniture")
    private String houseFurniture;

    @TableField(value = "house_desc")
    private String houseDesc;

    @TableField(value = "house_require")
    private String houseRequire;

    @TableField(value = "house_release_time")
    private Date houseReleaseTime;

    @TableField(value = "house_picture_url")
    private String housePictureUrl;

    @TableField(value = "house_title")
    private String houseTitle;

    public static final String COL_HOUSE_ID = "house_id";

    public static final String COL_HOUSE_USER_ID = "house_user_id";

    public static final String COL_HOUSE_TYPE = "house_type";

    public static final String COL_HOUSE_ORIENTATION = "house_orientation";

    public static final String COL_HOUSE_AREA = "house_area";

    public static final String COL_HOUSE_COST = "house_cost";

    public static final String COL_HOUSE_PAYMENT = "house_payment";

    public static final String COL_HOUSE_RENT_INCLUDE = "house_rent_include";

    public static final String COL_HOUSE_EARLY_TIME = "house_early_time";

    public static final String COL_HOUSE_NUM = "house_num";

    public static final String COL_HOUSE_DECORATION = "house_decoration";

    public static final String COL_HOUSE_LEASE_TYPE = "house_lease_type";

    public static final String COL_HOUSE_ADDRESS = "house_address";

    public static final String COL_HOUSE_FURNITURE = "house_furniture";

    public static final String COL_HOUSE_DESC = "house_desc";

    public static final String COL_HOUSE_REQUIRE = "house_require";

    public static final String COL_HOUSE_RELEASE_TIME = "house_release_time";

    public static final String COL_HOUSE_PICTURE_URL = "house_picture_url";

    public static final String COL_HOUSE_TITLE = "house_title";
}