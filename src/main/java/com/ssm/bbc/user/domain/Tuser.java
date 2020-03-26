package com.ssm.bbc.user.domain;

public class Tuser {
    private int userID;
    private String userName;
    private String userPwd;
    private String userEmail;
    private int inBlack;  //默认为0   1为加入黑名单
    private String question;
    private String answer;
    private int isOwner; //默认为0   1为版主, 可以有一个为2的作为内置管理员

    public Tuser() {

    }
    public Tuser(String userName, String userPwd, String userEmail, int inBlack, String question, String answer, int isOwner) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.inBlack = inBlack;
        this.question = question;
        this.answer = answer;
        this.isOwner = isOwner;
    }


    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getInBlack() {
        return inBlack;
    }

    public void setInBlack(int inBlack) {
        this.inBlack = inBlack;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(int isOwner) {
        this.isOwner = isOwner;
    }
}
