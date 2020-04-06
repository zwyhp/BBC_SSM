package com.ssm.bbc.message.controller;

import com.ssm.bbc.message.domain.Tmessage;
import com.ssm.bbc.message.service.ImessageService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private ImessageService imessageService;

    @GetMapping("/message")
    public Object getMessage(@RequestParam(value = "query", required = false) String query,
                             @RequestParam(value = "plate", required = false) String plate,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<Tmessage> tmessages = imessageService.queryNotPassMessageByPage(pageNum, pageSize,query,plate);
        return ResponseUtil.okList(tmessages);
    }


    @GetMapping("/getmessage/{id}")
    public Object getMessage(@PathVariable int id,HttpServletRequest request){
       Tmessage tmessage = imessageService.queryMessageById(id);
       return ResponseUtil.ok(tmessage);

    }


    @PutMapping("/checkMessage/{id}")
    public Object checkMessage(@PathVariable int id,HttpServletRequest request){
        int i = imessageService.checkMessage(id);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.CLICK_FAILED);
    }


    @PutMapping("/stickMessage/{id}")
    public Object stickMessage(@PathVariable int id,HttpServletRequest request){
        int i = imessageService.stickMessage(id);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.TOP_FAILED);
    }


    @DeleteMapping("/deleteMessage/{id}")
    public Object deleteMessage(@PathVariable int id,HttpServletRequest request){
        int i = imessageService.deleteMessage(id);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.DELETE_FAILED);
    }

}
