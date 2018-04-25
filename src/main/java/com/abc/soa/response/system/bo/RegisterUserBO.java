package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class RegisterUserBO extends TableBO implements Serializable {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像图片路径
     */
    private String userPicturePath;

    /**
     * 用户标签
     */
    private String tags;

    /**
     * 签名
     */
    private String signature;

    /**
     * 教育
     */
    private String education;

    /**
     * 毕业院校
     */
    private String graduate;

    /**
     * 行业
     */
    private String occupation;

    /**
     * 收入
     */
    private String income;

    /**
     * 工作经验
     */
    private String careerDuration;

    public String getCareerDuration() {
        return careerDuration;
    }

    public void setCareerDuration(String careerDuration) {
        this.careerDuration = careerDuration;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }
}
