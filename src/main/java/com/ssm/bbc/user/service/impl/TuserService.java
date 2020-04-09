package com.ssm.bbc.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssm.bbc.user.dao.TUserMapper;
import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.user.service.ItuserService;
import com.ssm.bbc.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TuserService implements ItuserService {
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 用户名的  id  inblac  inowner为默认的
     * @param user
     * @return
     */
    @Override
    public int addTuser(Tuser user) {
        Tuser userByName;
        userByName = tUserMapper.queryTuserByName(user.getUserName());
        BussinessUtil.isnotNull(userByName,BussinessUtil.USERNAME_REPETITION);
        Tuser userByEmail = tUserMapper.queryTuserByEmail(user.getUserEmail());
        BussinessUtil.isnotNull(userByEmail,BussinessUtil.EMAIL_REPETITION);
        user.setInBlack(0);
        user.setIsOwner(0);
        return tUserMapper.addTUser(user);
    }

    /**
     * 只允许修改密码
     * @param user
     * @return
     */
    @Override
    public int updateTuser(Tuser user) {
        Tuser tuser = queryTuserByName(user.getUserName());
        tuser.setUserPwd(user.getUserPwd());
        return tUserMapper.updateTuser(tuser);
    }

    @Override
    public int updateTuserBlackById(int userId) {
        Tuser userByID = tUserMapper.queryTuserByID(userId);
        BussinessUtil.isNull(userByID,BussinessUtil.USER_NOT_EXIST);
        if (userByID.getInBlack()==0){
            return tUserMapper.inBlackTuserById(userId);
        }else{
            return tUserMapper.outBlackTuserById(userId);
        }
    }

    @Override
    public int updateTuserOwnerById(int userId) {
        Tuser userByID = tUserMapper.queryTuserByID(userId);
        BussinessUtil.isNull(userByID,BussinessUtil.USER_NOT_EXIST);
        if (userByID.getIsOwner()==0){
            return tUserMapper.inOwnerTuserById(userId);
        }else{
            return tUserMapper.outOwnerTuserById(userId);
        }
    }

    @Override
    public int deleteTuserById(int userId) {
        return tUserMapper.deleteTuserById(userId);
    }

    @Override
    public Tuser queryTuserByName(String name) {
        Tuser userByName = tUserMapper.queryTuserByName(name);
        BussinessUtil.isNull(userByName,BussinessUtil.USER_NOT_EXIST);
        return userByName;
    }

    @Override
    public Tuser queryTuserByID(int userId) {
        return tUserMapper.queryTuserByID(userId);
    }

    /**
     * 使用分页插件
     * @param pageNum  当前页数
     * @param pageSize 每页显示数量
     * @param query  按名字查询
     * @return
     */
    @Override
    public List<Tuser> queryTUserByPage(int pageNum, int pageSize, String query) {
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<Tuser> users = tUserMapper.queryTUserByPage(query);
        return users;
    }
}
