package com.ssm.bbc.user.service;

import com.ssm.bbc.user.domain.Tuser;

import java.util.List;
import java.util.Map;

public interface ItuserService {
    int addTuser(Tuser user);
    int updateTuser(Tuser user);
    int updateTuserBlackById(int userId);
    int updateTuserOwnerById(int userId);
    int deleteTuserById(int userId);
    Tuser queryTuserByName(String name);
    Tuser queryTuserByID(int userId);
    List<Tuser> queryTUserByPage(int pageNum,int pageSize);
}
