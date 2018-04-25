package com.abc.soa.response.financed.bo;

import java.io.Serializable;
import java.util.Date;


public class InvoiceApprovalLog implements Serializable{
	/**PK**/
	private String id;

	/**领用流水号**/
	private String useId;

	/**审批结果**/
	private String approvalResult;

	/**审批意见**/
	private String approvalOpinions;

	/**审批人**/
	private String approver;

	/**审批时间**/
	private Date approverTime;

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getApprovalOpinions() {
		return approvalOpinions;
	}

	public void setApprovalOpinions(String approvalOpinions) {
		this.approvalOpinions = approvalOpinions;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApproverTime() {
		return approverTime;
	}

	public void setApproverTime(Date approverTime) {
		this.approverTime = approverTime;
	}
}
