package com.ssm.bbc.message.dao;

import com.ssm.bbc.message.domain.Tmessage;

import java.util.List;

public interface TmessageMapper {
    int addTmessage(Tmessage tmessage);
    int updateTmessageById(Tmessage tmessage);
    int deleteTmessageById(int messageId);
    int checkMessage(int messageId);
    int addClickNumber(int messageId);
    int stickMessage(int messageId);
    int cancelStickMessage(int messageId);
    List<Tmessage> queryTmessageByPage();
    Tmessage queryTmessageById(int messageId);
    List<Tmessage> queryNotPassMessageByPage();

}
