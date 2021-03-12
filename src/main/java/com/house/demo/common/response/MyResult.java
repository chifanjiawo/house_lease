package com.house.demo.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xjj
 */
@Data
public class MyResult implements Serializable {


    private int code;
    private String msg;
    private Object data;

    public static MyResult succ(Object data){
        return getIntance(200,"操作成功",data);
    }
    public static MyResult succ(String msg){
        return getIntance(200,msg,null);
    }

    public static MyResult fail(String msg){

        return getIntance(400,msg,null);
    }


    private static MyResult getIntance(int code, String msg, Object data){

        MyResult result = new MyResult();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);

        return result;
    }


}
