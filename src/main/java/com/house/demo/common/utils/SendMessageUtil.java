package com.house.demo.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xjj
 */

@Component
public class SendMessageUtil {

    @Autowired
    private RedisTemplate redisTemplate;


    private static final String KEY = "d41d8cd98f00b204e980";

    private static final String UID = "xjj180s";


    public Integer sendMessage(String username,String sendPhoneNum){

        RestTemplate restTemplate = new RestTemplate();
        String randomKey = getRandomKey();

        String desc = "验证码:"+randomKey;

        String url = "http://gbk.api.smschinese.cn/?Uid="+UID+"&Key="+KEY+"&smsMob="+sendPhoneNum+"&smsText="+desc;
        ResponseEntity<String> entity = restTemplate.postForEntity(url,null , String.class);
        saveKeyToRedis(username,randomKey);

        return entity.getStatusCodeValue();

    }

    public boolean validateCode(String username,String code){

        String key = getKeyFromRedis(username);

        if(key!=null){
            return key.equals(code);
        }
        return false;


    }

    private String getRandomKey(){

        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }

    private void saveKeyToRedis(String name,String key){

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(name,key,5,TimeUnit.MINUTES);

    }

    private String getKeyFromRedis(String name){

        return redisTemplate.opsForValue().get(name).toString();
    }



}
