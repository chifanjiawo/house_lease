package com.house.demo.utils;

import org.springframework.util.DigestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date()));

        System.out.println(Md5Util.encodeByMD5("1234567"));

        System.out.println(Md5Util.parseMD5("1234567","JoNHwfcea920f7412b5da7be0cf42b8c93759"));


    }
}



