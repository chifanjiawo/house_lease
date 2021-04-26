package com.house.demo.service.impl;

import com.house.demo.dao.MessageMapper;
import com.house.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xjj
 */

@Service

public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int getMessageNum(int id) {

        List<Integer> list = messageMapper.getMessageById(id);

        if(list!= null){
            for (int i :
                    list) {
                if (i == 0){
                    return 1;
                }
            }
        }
        return 0;


    }

    @Override
    public int delMessage(int id) {

        int i = messageMapper.delMessageById(id);


        return i;
    }

    @Override
    public int setRead(int id) {


        int i = messageMapper.updateMessage(id);

        return i;

    }
}
