package com.ssm.bbc.messcategory.controller;

import com.ssm.bbc.messcategory.domain.TmessCategory;
import com.ssm.bbc.messcategory.service.IMessCategoryService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MessCategoryController {
    @Autowired
    private IMessCategoryService messCategoryService;

    @GetMapping("/category")
    public Object users(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<TmessCategory> tmessCategories = messCategoryService.queryMessCategoryByPage(pageNum, pageSize);
        return tmessCategories;
    }

    @PostMapping("/category")
    public Object addCategory(@RequestBody @Validated TmessCategory TmessCategory){
        int i = messCategoryService.addMessCategory(TmessCategory);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.ADD_FAILED);
    }

    @PutMapping("/category")
    public Object updateCategory(@RequestBody @Validated TmessCategory TmessCategory){
        int i = messCategoryService.updateMessCategory(TmessCategory);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.UPDATE_FAILED);
    }

    @DeleteMapping("/category/{id}")
    public Object deleteCategory(@PathVariable int id){
        int i = messCategoryService.deleteMessCategoryById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.badArgument(BussinessUtil.DELETE_FAILED);
    }
}
