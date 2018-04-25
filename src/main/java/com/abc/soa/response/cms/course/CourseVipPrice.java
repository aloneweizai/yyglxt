package com.abc.soa.response.cms.course;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/27 09:46
 */
public class CourseVipPrice {

    /****varchar(64)**/
    private String id;

    /**课程ID**varchar(64)**/
    private String curriculumId;

    /**会员等级**varchar(10)**/
    private String vipGrade;

    /**会员价格**double**/
    private Double vipPrice;

    /**会员积分**double**/
    private Double vipIntegral;

    /**赠送积分**int(11)**/
    private Integer giveIntegral;

    /****datetime**/
    private java.util.Date createTime;

    /****datetime**/
    private java.util.Date lastUpdate;

    private List<CurriculumGiftBo> curriculumGiftBoList;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setCurriculumId(String curriculumId){
        this.curriculumId = curriculumId;
    }

    public String getCurriculumId(){
        return this.curriculumId;
    }

    public void setVipGrade(String vipGrade){
        this.vipGrade = vipGrade;
    }

    public String getVipGrade(){
        return this.vipGrade;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Double getVipIntegral() {
        return vipIntegral;
    }

    public void setVipIntegral(Double vipIntegral) {
        this.vipIntegral = vipIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral){
        this.giveIntegral = giveIntegral;
    }

    public Integer getGiveIntegral(){
        return this.giveIntegral;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    public void setLastUpdate(java.util.Date lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public java.util.Date getLastUpdate(){
        return this.lastUpdate;
    }


    public List<CurriculumGiftBo> getCurriculumGiftBoList() {
        return curriculumGiftBoList;
    }

    public void setCurriculumGiftBoList(List<CurriculumGiftBo> curriculumGiftBoList) {
        this.curriculumGiftBoList = curriculumGiftBoList;
    }
}
