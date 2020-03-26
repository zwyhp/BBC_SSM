package com.ssm.bbc.message.service;

import com.ssm.bbc.message.domain.Tcomment;
import com.ssm.bbc.message.domain.Tmessage;

import java.util.List;

public interface ImessageService {
    int addMessage(Tmessage tmessage);
    int addComment(Tcomment tcomment);
    int updateMessage(Tmessage tmessage);
    int deleteMessage(int messageId);

    int checkMessage(int messageId);
    int stickMessage(int messageId);
    List<Tmessage> queryMessageByPage(int pageNum,int pageSize);

    List<Tmessage> queryNotPassMessageByPage(int pageNum,int pageSize);

    Tmessage queryMessageById(int messageId);

    List<Tcomment> queryCommentByPage(int pageNum,int pageSize,int messageId);
}
