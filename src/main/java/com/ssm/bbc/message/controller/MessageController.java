package com.ssm.bbc.message.controller;

import com.ssm.bbc.message.domain.Tmessage;
import com.ssm.bbc.message.service.ImessageService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MessageController {
    @Autowired
    private ImessageService imessageService;

    @PostMapping("/message")
    public Object addMessage(@RequestBody Tmessage tmessage){
        int i = imessageService.addMessage(tmessage);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }

    @DeleteMapping("/message/{id}")
    public Object message(@PathVariable int id){
        Tmessage tmessage = imessageService.queryMessageById(id);
        int i = imessageService.deleteMessage(id);
        return  i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.DELETE_FAILED);
    }


}
