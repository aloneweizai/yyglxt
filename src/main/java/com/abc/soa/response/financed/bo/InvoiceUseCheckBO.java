package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;


public class InvoiceUseCheckBO implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/****/
	private String id;

	/**申请人**/
	private String applyUser;

	/**申请时间**/
	private Date applyTime;

	/**签发状态，0：未分发，1：已分发，2：已签收**/
	private String issueStatus;

	/**审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿**/
	private String examineStatus;

	/**分发人**/
	private String distributeUser;

	/**分发时间**/
	private Date distributeTime;

	/**签收人**/
	private String signUser;

	/**签收时间**/
	private Date signTime;

	/**备注**/
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

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

	public String getDistributeUser() {
		return distributeUser;
	}

	public void setDistributeUser(String distributeUser) {
		this.distributeUser = distributeUser;
	}

	public Date getDistributeTime() {
		return distributeTime;
	}

	public void setDistributeTime(Date distributeTime) {
		this.distributeTime = distributeTime;
	}

	public String getSignUser() {
		return signUser;
	}

	public void setSignUser(String signUser) {
		this.signUser = signUser;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
