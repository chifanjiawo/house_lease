package com.house.demo.service;

/**
 * @author xjj
 */
public interface MessageService {

    int getMessageNum( int id);

    int delMessage(int id);


    int setRead(int id);
}
