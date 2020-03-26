package com.ssm.bbc.message.domain;

import com.ssm.bbc.util.parameterverify.VerifyError;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Tcomment {
    private int commentId;
    @NotEmpty
    @NotEmpty(message = VerifyError.COMMENT_NOT_NULL)
    private String commentContent;
    private LocalDateTime commentTime;
    @NotNull(message = VerifyError.MESS_ID_NOT_NULL)
    private int messageId;
    private String commentUser;

    public Tcomment() {

    }

    public Tcomment(int commentIdl, String commentContent, LocalDateTime commentTime, int messageId, String commentUser) {
        this.commentId = commentIdl;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.messageId = messageId;
        this.commentUser = commentUser;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }
}
