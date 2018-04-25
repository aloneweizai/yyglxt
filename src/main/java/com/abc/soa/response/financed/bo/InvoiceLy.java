package com.abc.soa.response.financed.bo;

import java.util.Date;
import java.util.List;


public class InvoiceLy{
	/****/
	private String id;

	/**
	 * 申请人ID
	 **/
	private String applyUser;

	/**
	 * 申请人名称
	 */
	private String applyUserName;

	/**
	 * 申请时间
	 **/
	private Date applyTime;

	/**
	 * 签发状态，0：未分发，1：已分发，2：已签收
	 **/
	private String issueStatus;

	/**
	 * 审批状态，0：待审核，1：审核通过，2：审核不通过，3：草稿
	 **/
	private String examineStatus;

	/**
	 * 分发人
	 **/
	private String distributeUser;

	private String distributeUserName;

	/**
	 * 分发时间
	 **/
	private Date distributeTime;

	/**
	 * 签收人
	 **/
	private String signUser;

	private String signUserName;

	/**
	 * 签收时间
	 **/
	private Date signTime;

	/**
	 * 备注
	 **/
	private String remark;

	private Integer num;

	/**审核意见**/
	private String checkOpinion;

	private String applyBook;

	private List<InvoiceUseDetailBO> invoiceUseDetailBOList;
	private List<InvoiceApprovalLog> invoiceApprovalLogList;
	private List<InvoiceDistributeBO> invoiceDistributeBOList;

	private Date startTime;

	private Date endTime;


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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<InvoiceUseDetailBO> getInvoiceUseDetailBOList() {
		return invoiceUseDetailBOList;
	}

	public void setInvoiceUseDetailBOList(List<InvoiceUseDetailBO> invoiceUseDetailBOList) {
		this.invoiceUseDetailBOList = invoiceUseDetailBOList;
	}

	public List<InvoiceApprovalLog> getInvoiceApprovalLogList() {
		return invoiceApprovalLogList;
	}

	public void setInvoiceApprovalLogList(List<InvoiceApprovalLog> invoiceApprovalLogList) {
		this.invoiceApprovalLogList = invoiceApprovalLogList;
	}

	public List<InvoiceDistributeBO> getInvoiceDistributeBOList() {
		return invoiceDistributeBOList;
	}

	public void setInvoiceDistributeBOList(List<InvoiceDistributeBO> invoiceDistributeBOList) {
		this.invoiceDistributeBOList = invoiceDistributeBOList;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getDistributeUserName() {
		return distributeUserName;
	}

	public void setDistributeUserName(String distributeUserName) {
		this.distributeUserName = distributeUserName;
	}

	public String getSignUserName() {
		return signUserName;
	}

	public void setSignUserName(String signUserName) {
		this.signUserName = signUserName;
	}

	public String getApplyBook() {
		return applyBook;
	}

	public void setApplyBook(String applyBook) {
		this.applyBook = applyBook;
	}
}
