package com.house.demo.common.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @author xjj
 */
public class Md5Util {

    //生成md5
    public static String encodeByMD5(String originString){

        String md5str = getSalt()+ encodeFirst(originString);

        return md5str;
    }

    public static String encodeFirst(String str){

        String first = DigestUtils.md5DigestAsHex(str.getBytes());
        return first;

    }

    public static boolean parseMD5(String secret,String md5str){

        String salt = md5str.substring(0, 5);

        String result = DigestUtils.md5DigestAsHex(secret.getBytes());

        return md5str.equals(salt+result);

    }

    //加盐

    private static String getSalt(){

        char[] c = "ABCDEFGFHJKJNMOPqRSTUVWXYZabcdefgfhjkjnmopqrstuvwxyz0123456789".toCharArray();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 5; i++) {

            builder.append(c[new Random().nextInt(c.length)]);
        }
        return builder.toString();
    }


    public static void main(String[] args) {

        String s = Md5Util.encodeByMD5("1234567");

        System.out.println(s);
        System.out.println(parseMD5("1234567", s));

    }
}



