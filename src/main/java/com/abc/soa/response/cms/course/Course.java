package com.abc.soa.response.cms.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/17 14:44
 * 课程
 */
public class Course {

    /**课程ID**varchar(64)**/
    private String curriculumId;

    /**课程标题**varchar(200)**/
    private String title;

    /**浏览量**tinyint(11)**/
    private Integer browseNum;

    /**观看数**tinyint(11)**/
    private Integer watchNum;

    /**课程分类**varchar(64)**/
    private String classify;

    /**分类名称**varchar(100)**/
    private String classifyName;

    /**商品ID**varchar(64)**/
    private String goodsId;

    /**课程推荐**varchar(64)**/
    private String recommend;

    /**授课方式**varchar(64)**/
    private String teachingMethod;

    /**培训时间起**datetime**/
    private java.util.Date trainTimeBegin;

    /**培训时间止**datetime**/
    private java.util.Date trainTimeEnd;

    /**是否需要报名**tinyint(1)**/
    private Integer isApply;

    /**报名时间起**datetime**/
    private java.util.Date applyTimeBegin;

    /**报名时间止**datetime**/
    private java.util.Date applyTimeEnd;

    /**报名时交费**tinyint(1)**/
    private Integer applyPay;

    /**是否需要签到**tinyint(1)**/
    private Integer isSign;

    /**签到时间起**datetime**/
    private java.util.Date signTimeBegin;

    /**签到时间止**datetime**/
    private java.util.Date signTimeEnd;

    /**发布时间**datetime**/
    private java.util.Date issueTime;

    /**人数限制**int(11)**/
    private Integer peopleLimit;

    /**培训地点**varchar(200)**/
    private String trainSite;

    /**课程封面**varchar(200)**/
    private String cover;

    /**发布范围**varchar(64)**/
    private String issueScope;

    /**课程收费**tinyint(1)**/
    private Integer isFree;

    /**成本价格**double**/
    private Double costPrice;

    /**销售价格**double**/
    private Double sellPrice;

    /**市场价格**double**/
    private Double marketPrice;

    public double getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(double integralPrice) {
        this.integralPrice = integralPrice;
    }

    //    积分价格
    private double integralPrice;

//    /**订购价格原价**double**/
//    private Double originalPrice;
//
//    /**积分价格原价**double**/
//    private Double integralOriginalPrice;

    /**课程简介**longtext**/
    private String curriculumidIntro;

    /**课程状态**tinyint(1)**/
    private Integer status;

    private List<CourseLabel> labelList;

    private List<CourseMemberGrade> membergradeList;

    private List<String> memberGradeIds;

    private List<CourseLecturerRel> lecturerGxList;

    private List<CourseChapter> chapterBoList;

    private List<CourseVipPrice> uvipPriceBoList;

    public List<String> getMemberGradeIds() {
        List<String> rs = new ArrayList<>();
        if(membergradeList!=null && !membergradeList.isEmpty()){
            for (CourseMemberGrade grade: membergradeList){
                rs.add(grade.getMemberGrade());
            }
        }
        return rs;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public List<CourseChapter> getChapterBoList() {
        return chapterBoList;
    }

    public Course setChapterBoList(List<CourseChapter> chapterBoList) {
        this.chapterBoList = chapterBoList;
        return this;
    }

    public Course setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public Course setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
        return this;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public Course setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
        return this;
    }

    public String getClassify() {
        return classify;
    }

    public Course setClassify(String classify) {
        this.classify = classify;
        return this;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public Course setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getRecommend() {
        return recommend;
    }

    public Course setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public Course setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
        return this;
    }

    public Date getTrainTimeBegin() {
        return trainTimeBegin;
    }

    public Course setTrainTimeBegin(Date trainTimeBegin) {
        this.trainTimeBegin = trainTimeBegin;
        return this;
    }

    public Date getTrainTimeEnd() {
        return trainTimeEnd;
    }

    public Course setTrainTimeEnd(Date trainTimeEnd) {
        this.trainTimeEnd = trainTimeEnd;
        return this;
    }

    public Date getApplyTimeBegin() {
        return applyTimeBegin;
    }

    public Course setApplyTimeBegin(Date applyTimeBegin) {
        this.applyTimeBegin = applyTimeBegin;
        return this;
    }

    public Date getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public Course setApplyTimeEnd(Date applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
        return this;
    }

    public Date getSignTimeBegin() {
        return signTimeBegin;
    }

    public Course setSignTimeBegin(Date signTimeBegin) {
        this.signTimeBegin = signTimeBegin;
        return this;
    }

    public Date getSignTimeEnd() {
        return signTimeEnd;
    }

    public Course setSignTimeEnd(Date signTimeEnd) {
        this.signTimeEnd = signTimeEnd;
        return this;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public Course setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
        return this;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public Course setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
        return this;
    }

    public String getTrainSite() {
        return trainSite;
    }

    public Course setTrainSite(String trainSite) {
        this.trainSite = trainSite;
        return this;
    }

    public String getCover() {
        return cover;
    }

    public Course setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public String getIssueScope() {
        return issueScope;
    }

    public Course setIssueScope(String issueScope) {
        this.issueScope = issueScope;
        return this;
    }

    public String getCurriculumidIntro() {
        return curriculumidIntro;
    }

    public Course setCurriculumidIntro(String curriculumidIntro) {
        this.curriculumidIntro = curriculumidIntro;
        return this;
    }

    public List<CourseLabel> getLabelList() {
        return labelList;
    }

    public Course setLabelList(List<CourseLabel> labelList) {
        this.labelList = labelList;
        return this;
    }

    public List<CourseMemberGrade> getMembergradeList() {
        return membergradeList;
    }

    public Course setMembergradeList(List<CourseMemberGrade> membergradeList) {
        this.membergradeList = membergradeList;
        return this;
    }

    public List<CourseLecturerRel> getLecturerGxList() {
        return lecturerGxList;
    }

    public Course setLecturerGxList(List<CourseLecturerRel> lecturerGxList) {
        this.lecturerGxList = lecturerGxList;
        return this;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public Course setIsApply(Integer isApply) {
        this.isApply = isApply;
        return this;
    }

    public Integer getApplyPay() {
        return applyPay;
    }

    public Course setApplyPay(Integer applyPay) {
        this.applyPay = applyPay;
        return this;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public Course setIsSign(Integer isSign) {
        this.isSign = isSign;
        return this;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public Course setIsFree(Integer isFree) {
        this.isFree = isFree;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Course setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public Course setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

//    public Double getOriginalPrice() {
//        return originalPrice;
//    }
//
//    public void setOriginalPrice(Double originalPrice) {
//        this.originalPrice = originalPrice;
//    }
//
//    public Double getIntegralOriginalPrice() {
//        return integralOriginalPrice;
//    }
//
//    public void setIntegralOriginalPrice(Double integralOriginalPrice) {
//        this.integralOriginalPrice = integralOriginalPrice;
//    }

    public void setMemberGradeIds(List<String> memberGradeIds) {
        this.memberGradeIds = memberGradeIds;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public Course setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Course setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
        return this;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public Course setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
        return this;
    }

    public List<CourseVipPrice> getUvipPriceBoList() {
        return uvipPriceBoList;
    }

    public void setUvipPriceBoList(List<CourseVipPrice> uvipPriceBoList) {
        this.uvipPriceBoList = uvipPriceBoList;
    }
}