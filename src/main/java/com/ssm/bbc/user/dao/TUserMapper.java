package com.ssm.bbc.user.dao;

import com.ssm.bbc.user.domain.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper {
    int inBlackTuserById(int userId);
    int inOwnerTuserById(int userId);
    int outBlackTuserById(int userId);
    int outOwnerTuserById(int userId);
    //因为是否只有2种状态 所以写死了0为否，1为是
    int deleteTuserById(int userId);
    List<Tuser> queryTUserByPage(@Param(value="query")String query);
    int addTUser(Tuser user);
    int updateTuser(Tuser user);
    Tuser queryTuserByName(String name);
    Tuser queryTuserByEmail(String email);
    Tuser queryTuserByID(int userId);
}
