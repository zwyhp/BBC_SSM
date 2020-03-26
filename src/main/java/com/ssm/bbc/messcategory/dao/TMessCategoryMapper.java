package com.ssm.bbc.messcategory.dao;

import com.ssm.bbc.messcategory.domain.TmessCategory;

import java.util.List;

public interface TMessCategoryMapper {
    int addTMessCategory(TmessCategory category);
    int updateTMessCategoryById(TmessCategory category);
    int deleteTMessCategoryById(int categoryId);
    List<TmessCategory> queryTMessCategoryByPage();
    TmessCategory queryTMessCategoryById(int categoryId);
    TmessCategory queryTMessCategoryByName(String category);
    TmessCategory queryTMessCategoryByOwner(String owner);
}
