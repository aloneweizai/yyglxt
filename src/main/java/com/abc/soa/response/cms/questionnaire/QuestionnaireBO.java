package com.abc.soa.response.cms.questionnaire;


import java.util.List;

/**
 * 
 * 问卷表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionnaireBO {

	private String id;
	private String title;
	private String simpleDesc;
	private String status;
	private String createUser;
	private java.util.Date createTime;
	private java.util.Date updateTime;
	private String updateUser;
	private Integer recoveryRate;
	private Integer accessRate;
	private Double accessNum;
	private String sceneCode;
	private String tradeCode;
	private String skinUrl;
	private String endDesc;

    private QuestionnaireParam questionnaireParam;

	private List<SubjectsBO> subjectsBOList;

	public List<SubjectsBO> getSubjectsBOList() {
		return subjectsBOList;
	}

	public void setSubjectsBOList(List<SubjectsBO> subjectsBOList) {
		this.subjectsBOList = subjectsBOList;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setSimpleDesc(String simpleDesc){
		this.simpleDesc = simpleDesc;
	}

	public String getSimpleDesc(){
		return this.simpleDesc;
	}

	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}

	public String getCreateUser(){
		return this.createUser;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser = updateUser;
	}

	public String getUpdateUser(){
		return this.updateUser;
	}

	public void setRecoveryRate(Integer recoveryRate){
		this.recoveryRate = recoveryRate;
	}

	public Integer getRecoveryRate(){
		return this.recoveryRate;
	}

	public void setAccessRate(Integer accessRate){
		this.accessRate = accessRate;
	}

	public Integer getAccessRate(){
		return this.accessRate;
	}

	public void setSceneCode(String sceneCode){
		this.sceneCode = sceneCode;
	}

	public String getSceneCode(){
		return this.sceneCode;
	}

	public void setTradeCode(String tradeCode){
		this.tradeCode = tradeCode;
	}

	public String getTradeCode(){
		return this.tradeCode;
	}

    public QuestionnaireParam getQuestionnaireParam() {
        return questionnaireParam;
    }

    public void setQuestionnaireParam(QuestionnaireParam questionnaireParam) {
        this.questionnaireParam = questionnaireParam;
    }

	public String getSkinUrl() {
		return skinUrl;
	}

	public void setSkinUrl(String skinUrl) {
		this.skinUrl = skinUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAccessNum() {
		return accessNum;
	}

	public QuestionnaireBO setAccessNum(Double accessNum) {
		this.accessNum = accessNum;
		return this;
	}

	public String getEndDesc() {
		return endDesc;
	}

	public QuestionnaireBO setEndDesc(String endDesc) {
		this.endDesc = endDesc;
		return this;
	}
}
