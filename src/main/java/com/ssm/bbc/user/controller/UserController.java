package com.ssm.bbc.user.controller;

import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.user.service.ItuserService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;

@RestController
public class UserController {
    @Autowired
    private ItuserService ituserService;

    @PostMapping("/user")
    public Object addUser(@RequestBody Tuser tuser){
        int i = ituserService.addTuser(tuser);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }



}
