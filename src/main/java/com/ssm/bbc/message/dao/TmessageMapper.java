package com.ssm.bbc.message.dao;

import com.ssm.bbc.message.domain.Tmessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TmessageMapper {
    int addTmessage(Tmessage tmessage);
    int updateTmessageById(Tmessage tmessage);
    int deleteTmessageById(int messageId);
    int checkMessage(int messageId);
    int cancelCheckMessage(int messageId);
    int addClickNumber(int messageId);
    int stickMessage(int messageId);
    int cancelStickMessage(int messageId);
    List<Tmessage> queryTmessageTop10(@Param("query") String query);
    List<Tmessage> queryTmessageByPage(Map<String,Object> map);
    Tmessage queryTmessageById(int messageId);

    /**
     * map  plate ：板块
     *      query ；查询条件
     * @param map
     * @return
     */
    List<Tmessage> queryNotPassMessageByPage(Map<String,Object> map);

}
