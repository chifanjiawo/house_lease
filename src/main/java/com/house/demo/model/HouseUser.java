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
@TableName(value = "house_user")
public class HouseUser {
    private static final long serialVersionUID = 5636524409799996936L;
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_nick_name")
    private String userNickName;

    @TableField(value = "user_password")
    private String userPassword;

    @TableField(value = "user_last_login_time")
    private Date userLastLoginTime;

    @TableField(value = "user_tel")
    private String userTel;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_avatar")
    private String userAvatar;

    @TableField(value = "user_register_time")
    private Date userRegisterTime;

    @TableField(value = "user_role")
    private String userRole;

    @TableField(value = "user_ban_status")
    private Byte userBanStatus;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_NICK_NAME = "user_nick_name";

    public static final String COL_USER_PASSWORD = "user_password";

    public static final String COL_USER_LAST_LOGIN_TIME = "user_last_login_time";

    public static final String COL_USER_TEL = "user_tel";

    public static final String COL_USER_EMAIL = "user_email";

    public static final String COL_USER_AVATAR = "user_avatar";

    public static final String COL_USER_REGISTER_TIME = "user_register_time";

    public static final String COL_USER_ROLE = "user_role";

    public static final String COL_USER_BAN_STATUS = "user_ban_status";
}