package com.ssm.bbc.message.domain;

import java.time.LocalDateTime;

public class Tcomment {
    private int commentId;
    private String commentContent;
    private LocalDateTime commentTime;
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
