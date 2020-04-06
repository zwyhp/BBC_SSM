package com.ssm.bbc.message.controller;

import com.ssm.bbc.message.domain.Tmessage;
import com.ssm.bbc.message.service.ImessageService;
import com.ssm.bbc.user.domain.Tuser;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private ImessageService imessageService;

    @GetMapping("/tourist/messagetop")
    public Object getMessageTop10(@RequestParam(value = "query", required = false) String query){
        List<Tmessage> tmessages = imessageService.queryMessageTop10(query);
        return ResponseUtil.ok(tmessages);
    }

    @GetMapping("/tourist/message")
    public Object getMessage(@RequestParam(value = "query", required = false) String query,
                             @RequestParam(value = "plate", required = false) String plate,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<Tmessage> tmessages = imessageService.queryMessageByPage(pageNum, pageSize,query, plate);
        return ResponseUtil.okList(tmessages);
    }

    @GetMapping("/tourist/message/{id}")
    public Object getMessage(@PathVariable("id") int id){
        Tmessage tmessages = imessageService.queryMessageById(id);
        return ResponseUtil.ok(tmessages);
    }

    @PostMapping("/user/message")
    public Object addMessage(@RequestBody @Validated Tmessage tmessage,
                             HttpServletRequest request){
        Tuser user = (Tuser) request.getSession().getAttribute("user");
        tmessage.setGuestName(user.getUserName());
        int i = imessageService.addMessage(tmessage);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }

    @DeleteMapping("/user/message/{id}")
    public Object message(@PathVariable int id,HttpServletRequest request){
        Tuser username = (Tuser)request.getSession().getAttribute("username");
        int i = imessageService.deleteMessage(id);
        return  i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.DELETE_FAILED);
    }


}
