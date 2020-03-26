package com.ssm.bbc.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.bbc.message.dao.TcommentMapper;
import com.ssm.bbc.message.dao.TmessageMapper;
import com.ssm.bbc.message.domain.Tcomment;
import com.ssm.bbc.message.domain.Tmessage;
import com.ssm.bbc.message.service.ImessageService;
import com.ssm.bbc.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MessageService implements ImessageService {
    @Autowired
    private TcommentMapper tcommentMapper;
    @Autowired
    private TmessageMapper tmessageMapper;


    @Override
    public int addMessage(Tmessage tmessage) {
        tmessage.setIsPass(0);
        tmessage.setIsTop(0);
        tmessage.setClickNumber(0);
        tmessage.setMessageTime(LocalDateTime.now());
        tmessage.setLastCommentTime(LocalDateTime.now());
        return tmessageMapper.addTmessage(tmessage);
    }

    @Override
    public int addComment(Tcomment tcomment) {
        Tmessage tmessage = tmessageMapper.queryTmessageById(tcomment.getMessageId());
        BussinessUtil.isNull(tmessage,BussinessUtil.MESSAGE_IS_NULL);
        tmessage.setLastCommentTime(LocalDateTime.now());
        tcomment.setCommentTime(LocalDateTime.now());
        updateMessage(tmessage);
        return tcommentMapper.addTcomment(tcomment);
    }

    @Override
    public int updateMessage(Tmessage tmessage) {
        return tmessageMapper.updateTmessageById(tmessage);
    }

    @Override
    public int deleteMessage(int messageId) {
        return tmessageMapper.deleteTmessageById(messageId);
    }

    @Override
    public int checkMessage(int messageId) {
        return tmessageMapper.checkMessage(messageId);
    }

    @Override
    public int stickMessage(int messageId) {
        Tmessage tmessage = tmessageMapper.queryTmessageById(messageId);
        if (tmessage.getIsTop()==0) {
            return tmessageMapper.stickMessage(messageId);
        } else{
            return tmessageMapper.cancelStickMessage(messageId);
        }
    }


    @Override
    public List<Tmessage> queryMessageByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return tmessageMapper.queryTmessageByPage();
    }

    @Override
    public List<Tmessage> queryNotPassMessageByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return tmessageMapper.queryNotPassMessageByPage();
    }

    @Override
    public Tmessage queryMessageById(int messageId) {
        Tmessage tmessage = tmessageMapper.queryTmessageById(messageId);
        if (tmessage != null){
        tmessageMapper.addClickNumber(messageId);
        }
        return tmessage;
    }

    @Override
    public List<Tcomment> queryCommentByPage(int pageNum, int pageSize ,int messageId) {
        PageHelper.startPage(pageNum, pageSize);
        return tcommentMapper.queryTcommentByPage(messageId);
    }
}
