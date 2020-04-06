package com.ssm.bbc.messcategory.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.bbc.messcategory.dao.TMessCategoryMapper;
import com.ssm.bbc.messcategory.domain.TmessCategory;
import com.ssm.bbc.messcategory.service.IMessCategoryService;
import com.ssm.bbc.user.dao.TUserMapper;
import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessCategoryService implements IMessCategoryService {
    @Autowired
    private TMessCategoryMapper tMessCategoryMapper;
    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 增加版块时，需要把版主的是否为版主更新
     * @param category
     * @return
     */
    @Override
    public int addMessCategory(TmessCategory category) {
        verifyCategory(category,0);
        Tuser tuserByName = tUserMapper.queryTuserByName(category.getCategoryOwner());
        tUserMapper.inOwnerTuserById(tuserByName.getUserId());
        return tMessCategoryMapper.addTMessCategory(category);
    }

    @Override
    public int updateMessCategory(TmessCategory category) {
        verifyCategory(category,1);
        return tMessCategoryMapper.updateTMessCategoryById(category);
    }

    @Override
    public int deleteMessCategoryById(int categoryId) {
        return tMessCategoryMapper.deleteTMessCategoryById(categoryId);
    }

    @Override
    public List<TmessCategory> queryMessCategoryByPage(int pageNum, int pageSize, String query) {
        PageHelper.startPage(pageNum, pageSize);
        List<TmessCategory> users = tMessCategoryMapper.queryTMessCategoryByPage(query);
        return users;
    }

    @Override
    public List<TmessCategory> queryMessCategoryByPage() {
        return tMessCategoryMapper.queryTMessCategoryByPage("");
    }

    @Override
    public TmessCategory queryMessCategoryById(int categoryId) {
        return tMessCategoryMapper.queryTMessCategoryById(categoryId);
    }
    @Override
    public TmessCategory queryMessCategoryByOwner(String ownerName){
        return tMessCategoryMapper.queryTMessCategoryByOwner(ownerName);
    }

    /**
     * 板块名称不能重复，一个版主不能管理2个板块，增加与修改时需要验证
     * x   来判断  增加  还是修改
     * @param category
     */
    private void verifyCategory(TmessCategory category,int x){
        TmessCategory tmessCategory = tMessCategoryMapper.queryTMessCategoryByName(category.getCategory());
        BussinessUtil.isnotNull(tmessCategory,BussinessUtil.CATEGORY_REPETITION);
        TmessCategory tMessCategoryByOwner = tMessCategoryMapper.queryTMessCategoryByOwner(category.getCategoryOwner());
        if (x == 0){
            BussinessUtil.isnotNull(tMessCategoryByOwner, BussinessUtil.OWNER_REPETITION);
        }
        if(x == 1 && category.getCategoryId() != tMessCategoryByOwner.getCategoryId()) {
            BussinessUtil.isnotNull(tMessCategoryByOwner, BussinessUtil.OWNER_REPETITION);
        }
    }

    /**
     * 处理新旧版主的   权限问题
     * 旧的取消     新的加上
     * @param category
     */
    private void setCategoryOwner(TmessCategory category){
        TmessCategory tmessCategory = tMessCategoryMapper.queryTMessCategoryById(category.getCategoryId());
        Tuser oldUser = tUserMapper.queryTuserByName(tmessCategory.getCategoryOwner());
        tUserMapper.outOwnerTuserById(oldUser.getUserId());
        Tuser newUser = tUserMapper.queryTuserByName(category.getCategoryOwner());
        tUserMapper.inOwnerTuserById(newUser.getUserId());
    }

}
