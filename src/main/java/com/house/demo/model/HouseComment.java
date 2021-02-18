package com.house.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author mac
 */
@Data
@TableName(value = "house_comment")
public class HouseComment implements Serializable {

    private static final long serialVersionUID = -2274521796141395785L;
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    @TableField(value = "comment_pid")
    private Integer commentPid;

    @TableField(value = "comment_house_id")
    private Long commentHouseId;

    @TableField(value = "comment_author_id")
    private Integer commentAuthorId;

    @TableField(value = "comment_author_name")
    private String commentAuthorName;

    @TableField(value = "comment_author_avatar")
    private String commentAuthorAvatar;

    @TableField(value = "comment_content")
    private String commentContent;

    @TableField(value = "comment_create_time")
    private Date commentCreateTime;

    public static final String COL_COMMENT_ID = "comment_id";

    public static final String COL_COMMENT_PID = "comment_pid";

    public static final String COL_COMMENT_HOUSE_ID = "comment_house_id";

    public static final String COL_COMMENT_AUTHOR_ID = "comment_author_id";

    public static final String COL_COMMENT_AUTHOR_NAME = "comment_author_name";

    public static final String COL_COMMENT_AUTHOR_AVATAR = "comment_author_avatar";

    public static final String COL_COMMENT_CONTENT = "comment_content";

    public static final String COL_COMMENT_CREATE_TIME = "comment_create_time";
}