package com.abc.soa.response.cms.course;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 课程表
 * 
 **/
@SuppressWarnings("serial")
public class CurriculumsyBo implements Serializable {

	/**课程ID**varchar(64)**/
	private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

    /**商品ID**varchar(64)**/
    private String goodsId;

	/**发布时间**datetime**/
	private Date issueTime;

	/**课程封面**varchar(200)**/
	private String cover;

	/**课程收费**tinyint(1)**/
	private Integer isFree;

	/**课程简介**longtext**/
	private String curriculumidIntro;

    /**好评数**int**/
    private Integer evaluateNum45;

    /**中等评数**int**/
    private Integer evaluateNum23;

    /**差评论数**int**/
    private Integer evaluateNum1;

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    /**总评论数**int**/

    private Integer evaluateNum;

    /**授课方式**varchar(64)**/
    private String teachingMethod;


    /**培训时间起**datetime**/
    private Date trainTimeBegin;

    /**培训时间止**datetime**/
    private Date trainTimeEnd;

    /**是否需要报名**tinyint(1)**/
    private Integer isApply;

    /**人数限制**int(11)**/
    private Integer peopleLimit;

    /**报名时间起**datetime**/
    private Date applyTimeBegin;

    /**报名时间止**datetime**/
    private Date applyTimeEnd;

    /**报名时交费**tinyint(1)**/
    private Integer applyPay;

    /**是否需要签到**tinyint(1)**/
    private Integer isSign;

    /**签到时间起**datetime**/
    private Date signTimeBegin;

    /**签到时间止**datetime**/
    private Date signTimeEnd;




    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getCurriculumidIntro() {
        return curriculumidIntro;
    }

    public void setCurriculumidIntro(String curriculumidIntro) {
        this.curriculumidIntro = curriculumidIntro;
    }

    public Integer getEvaluateNum45() {
        return evaluateNum45;
    }

    public void setEvaluateNum45(Integer evaluateNum45) {
        this.evaluateNum45 = evaluateNum45;
    }

    public Integer getEvaluateNum23() {
        return evaluateNum23;
    }

    public void setEvaluateNum23(Integer evaluateNum23) {
        this.evaluateNum23 = evaluateNum23;
    }

    public Integer getEvaluateNum1() {
        return evaluateNum1;
    }

    public void setEvaluateNum1(Integer evaluateNum1) {
        this.evaluateNum1 = evaluateNum1;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }


    public Date getTrainTimeBegin() {
        return trainTimeBegin;
    }

    public void setTrainTimeBegin(Date trainTimeBegin) {
        this.trainTimeBegin = trainTimeBegin;
    }

    public Date getTrainTimeEnd() {
        return trainTimeEnd;
    }

    public void setTrainTimeEnd(Date trainTimeEnd) {
        this.trainTimeEnd = trainTimeEnd;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public Date getApplyTimeBegin() {
        return applyTimeBegin;
    }

    public void setApplyTimeBegin(Date applyTimeBegin) {
        this.applyTimeBegin = applyTimeBegin;
    }

    public Date getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(Date applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public Integer getApplyPay() {
        return applyPay;
    }

    public void setApplyPay(Integer applyPay) {
        this.applyPay = applyPay;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Date getSignTimeBegin() {
        return signTimeBegin;
    }

    public void setSignTimeBegin(Date signTimeBegin) {
        this.signTimeBegin = signTimeBegin;
    }

    public Date getSignTimeEnd() {
        return signTimeEnd;
    }

    public void setSignTimeEnd(Date signTimeEnd) {
        this.signTimeEnd = signTimeEnd;
    }


}
