package com.house.demo.utils;

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
public class MessageUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String uid="xjj180s";

    private static String secretKey="d41d8cd98f00b204e980";


    public int sendMessage(String iphoneNum){

        String randomKey = getRandomKey();

        String url = "http://gbk.api.smschinese.cn/?Uid="+uid+"&Key="+secretKey+"&smsMob="+iphoneNum+"&smsText="+ randomKey;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> entity = restTemplate.postForEntity(url, null, String.class);

        int i = entity.getStatusCodeValue();
        if(i>0){
            setRandomKey(iphoneNum,randomKey);
        }
        return i;
    }

    public boolean validateCode(String num,String code){

        String s = getRandomKey(num);

        if(s!=null){
            return s.equals(code);
        }
        return false;
    }


    private  String getRandomKey(){

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();

    }

    private void setRandomKey(String iphoneNum,String code){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(iphoneNum,code,3,TimeUnit.MINUTES);
    }

    private void delKey(String num){

        redisTemplate.delete(num);

    }

    private String getRandomKey(String num){
        return (String) redisTemplate.opsForValue().get(num);

    }

}
