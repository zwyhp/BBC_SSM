package com.ssm.bbc.messcategory.service;


import com.ssm.bbc.messcategory.domain.TmessCategory;

import java.util.List;

public interface IMessCategoryService {
    int addMessCategory(TmessCategory category);
    int updateMessCategory(TmessCategory category);
    int deleteMessCategoryById(int categoryId);
    List<TmessCategory> queryMessCategoryByPage(int pageNum,int pageSize);
    TmessCategory queryMessCategoryById(int categoryId);

}
