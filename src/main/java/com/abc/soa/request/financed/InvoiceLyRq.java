package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class InvoiceLyRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 签发状态，0：未分发，1：已分发，2：已签收
	 **/
	private String issueStatus;
	/**
	 * 审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿
	 **/
	private String examineStatus;
	/**
	 * 申请人ID
	 **/
	private String applyUser;
	//申请时间起
	private String startTime;
	//申请时间止
	private String endTime;


	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
}
