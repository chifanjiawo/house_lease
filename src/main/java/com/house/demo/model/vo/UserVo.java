package com.house.demo.model.vo;


import com.house.demo.model.HouseComment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xjj
 */
@Data
public class UserVo implements Serializable {

    private int userId;

    private String userName;

    private String userNickName;
    private String userTel;

    private Date userLastLoginTime;

    private String userAvatar;

    private List<OrderVo> orderList;

    private List<OrderVo> startList;

    private List<HouseComment> commentList;

}
