package com.house.demo.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author xjj
 */
public class Md5Util {

    public static String encodeByMD5(String originString){
        String md5str = DigestUtils.md5DigestAsHex(originString.getBytes());
        return md5str;
    }


    public static void main(String[] args) {
        System.out.println(Md5Util.encodeByMD5("1234567"));
    }
}



