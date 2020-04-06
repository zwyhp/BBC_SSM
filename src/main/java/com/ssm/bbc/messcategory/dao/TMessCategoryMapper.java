package com.ssm.bbc.messcategory.dao;

import com.ssm.bbc.messcategory.domain.TmessCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TMessCategoryMapper {
    int addTMessCategory(TmessCategory category);
    int updateTMessCategoryById(TmessCategory category);
    int deleteTMessCategoryById(int categoryId);
    List<TmessCategory> queryTMessCategoryByPage(@Param(value="query")String query);
    TmessCategory queryTMessCategoryById(int categoryId);
    TmessCategory queryTMessCategoryByName(String category);
    TmessCategory queryTMessCategoryByOwner(String owner);
}
