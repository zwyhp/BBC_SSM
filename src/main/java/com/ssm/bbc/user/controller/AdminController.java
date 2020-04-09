package com.ssm.bbc.user.controller;

import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.user.service.ItuserService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员 对用户的操作
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ItuserService ituserService;

    @GetMapping("/users")
    public Object users(@RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<Tuser> tusers = ituserService.queryTUserByPage(pageNum, pageSize,query);
        return ResponseUtil.okList(tusers);
    }

    @PutMapping("/updateBlack/{id}")
    public Object updateBlack(@PathVariable int id){
        int i = ituserService.updateTuserBlackById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.UPDATE_FAILED);
    }

    @PutMapping("/updateOwner/{id}")
    public Object updateOwner(@PathVariable int id){
        int i = ituserService.updateTuserOwnerById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.UPDATE_FAILED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public Object deleteUser(@PathVariable int id){
        int i = ituserService.deleteTuserById(id);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.DELETE_FAILED);
    }


}
