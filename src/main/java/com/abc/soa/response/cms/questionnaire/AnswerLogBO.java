package com.abc.soa.response.cms.questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/5 15:21
 */
public class AnswerLogBO {

    /****/
    private String id;

    /**问卷ID**/
    private String questionId;

    /**用户ID**/
    private String userId;

    /**微信ID**/
    private String weixinId;

    /**接入终端**/
    private String accessTerminal;

    /**IP地址**/
    private String ipAddress;

    /**答题开始时间**/
    private java.util.Date startTime;

    /**答题结束时间**/
    private java.util.Date endTime;

    private Answer answer;

    private List<Answer> answerList = new ArrayList<Answer>();

    /**
     * 平均用时
     **/
    private Long avgTimeLong;

    /**平均用时**/
    private String avgTime;

    private String subjectsId;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }

    public String getQuestionId(){
        return this.questionId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setAccessTerminal(String accessTerminal){
        this.accessTerminal = accessTerminal;
    }

    public String getAccessTerminal(){
        return this.accessTerminal;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public String getIpAddress(){
        return this.ipAddress;
    }

    public void setStartTime(java.util.Date startTime){
        this.startTime = startTime;
    }

    public java.util.Date getStartTime(){
        return this.startTime;
    }

    public void setEndTime(java.util.Date endTime){
        this.endTime = endTime;
    }

    public java.util.Date getEndTime(){
        return this.endTime;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }

    public Long getAvgTimeLong() {
        return avgTimeLong;
    }

    public void setAvgTimeLong(Long avgTimeLong) {
        this.avgTimeLong = avgTimeLong;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public AnswerLogBO setWeixinId(String weixinId) {
        this.weixinId = weixinId;
        return this;
    }
}
