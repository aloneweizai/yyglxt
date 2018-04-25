package com.abc.soa.response.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/19 16:38
 * 专家大侠
 */
public class QuestionExpertBo {

    private String id;

    /*用户ID*/
    private String userId;

    /*用户名称*/
    private String username;

    /*用户图片*/
    private String userImage;

    /*用户昵称*/
    private String nickname;

    /*真实名称*/
    private String realName;

    /*联系电话*/
    private String phone;

    /*专家类型  财税大侠，税务大侠*/
    private String type;

    /*工作年长*/
    private Integer yearWork;

    /*状态*/
    private String status;

    /*擅长领域*/
    private String goodField;

    /**专家简介**longtext**/
    private String intro;

    public String getId() {
        return id;
    }

    public QuestionExpertBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public QuestionExpertBo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public QuestionExpertBo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public QuestionExpertBo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public QuestionExpertBo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionExpertBo setStatus(String status) {
        this.status = status;
        return this;
    }


    public String getIntro() {
        return intro;
    }

    public QuestionExpertBo setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public String getType() {
        return type;
    }

    public QuestionExpertBo setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getYearWork() {
        return yearWork;
    }

    public QuestionExpertBo setYearWork(Integer yearWork) {
        this.yearWork = yearWork;
        return this;
    }

    public String getGoodField() {
        return goodField;
    }

    public QuestionExpertBo setGoodField(String goodField) {
        this.goodField = goodField;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public QuestionExpertBo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUserImage() {
        return userImage;
    }

    public QuestionExpertBo setUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }
}
