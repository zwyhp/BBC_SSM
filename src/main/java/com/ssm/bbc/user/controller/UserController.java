package com.ssm.bbc.user.controller;

import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.user.service.ItuserService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.MD5hexUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private ItuserService ituserService;

    /**
     * 对密码和安全问题答案   进行 md5加密后在进行注册
     * @param tuser
     * @return
     */
    @PostMapping("/register")
    public Object addUser(@RequestBody @Validated Tuser tuser){
        tuser.setUserPwd(MD5hexUtil.getMd5hex(tuser.getUserName(),tuser.getUserPwd()));
        tuser.setAnswer(MD5hexUtil.getMd5hex(tuser.getUserName(),tuser.getAnswer()));
        int i = ituserService.addTuser(tuser);
        return i>0? ResponseUtil.ok() : ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }

    @PostMapping("/login")
    public Object login(@RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password,
                        HttpServletRequest request){
        Tuser userByName = ituserService.queryTuserByName(username);
        String pwd = MD5hexUtil.getMd5hex(username,password);
        if(userByName.getUserPwd().equals(pwd)){
            request.getSession().setAttribute("user",userByName);
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.badArgument(BussinessUtil.PWD_ERROR);
        }
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResponseUtil.ok();
    }


}
