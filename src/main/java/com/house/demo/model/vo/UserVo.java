package com.house.demo.model.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xjj
 */
@Data
public class UserVo implements Serializable {

    private int id;

    private String name;

    private List<OrderVo> orderList;

    private List<OrderVo> startList;

    private List<CommentVo> commentList;

}
