package com.ssm.bbc.message.controller;

import com.ssm.bbc.message.domain.Tcomment;
import com.ssm.bbc.message.domain.Tmessage;
import com.ssm.bbc.message.service.ImessageService;
import com.ssm.bbc.messcategory.service.IMessCategoryService;
import com.ssm.bbc.util.BussinessUtil;
import com.ssm.bbc.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist")
public class CommentController {
    @Autowired
    private ImessageService imessageService;

    /**
     * 未做   用户
     * @param tcomment
     * @return
     */
    @PostMapping("/comment")
    public Object addComment(@RequestBody Tcomment tcomment){
        int i = imessageService.addComment(tcomment);
        return i>0? ResponseUtil.ok():ResponseUtil.afterError(BussinessUtil.ADD_FAILED);
    }

    @GetMapping("/message")
    public Object getMessage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<Tmessage> tmessages = imessageService.queryMessageByPage(pageNum, pageSize);
        return ResponseUtil.ok(tmessages);
    }

    @GetMapping("/comment")
    public Object getComment(@RequestParam(value = "messageId",required = true) int messageId,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        List<Tcomment> tcomments = imessageService.queryCommentByPage(pageNum, pageSize, messageId);
        return ResponseUtil.ok(tcomments);
    }
}
