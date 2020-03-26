package com.ssm.bbc.messcategory.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.bbc.messcategory.dao.TMessCategoryMapper;
import com.ssm.bbc.messcategory.domain.TmessCategory;
import com.ssm.bbc.messcategory.service.IMessCategoryService;
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

    @Override
    public int addMessCategory(TmessCategory category) {
        verifyCategory(category);
        return tMessCategoryMapper.addTMessCategory(category);
    }

    @Override
    public int updateMessCategory(TmessCategory category) {
        verifyCategory(category);
        return tMessCategoryMapper.updateTMessCategoryById(category);
    }

    @Override
    public int deleteMessCategoryById(int categoryId) {
        return tMessCategoryMapper.deleteTMessCategoryById(categoryId);
    }

    @Override
    public List<TmessCategory> queryMessCategoryByPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TmessCategory> users = tMessCategoryMapper.queryTMessCategoryByPage();
        return users;
    }

    @Override
    public TmessCategory queryMessCategoryById(int categoryId) {
        return tMessCategoryMapper.queryTMessCategoryById(categoryId);
    }

    /**
     * 板块名称不能重复，一个版主不能管理2个板块，增加与修改时需要验证
     * @param category
     */
    private void verifyCategory(TmessCategory category){
        TmessCategory tmessCategory = tMessCategoryMapper.queryTMessCategoryByName(category.getCategory());
        BussinessUtil.isnotNull(tmessCategory,BussinessUtil.CATEGORY_REPETITION);
        TmessCategory tMessCategoryByOwner = tMessCategoryMapper.queryTMessCategoryByOwner(category.getCategoryOwner());
        if(category.getCategoryId() != tMessCategoryByOwner.getCategoryId()) {
            BussinessUtil.isnotNull(tMessCategoryByOwner, BussinessUtil.OWNER_REPETITION);
        }
    }

}
