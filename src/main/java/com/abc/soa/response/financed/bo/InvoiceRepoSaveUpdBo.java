package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InvoiceRepoSaveUpdBo extends BaseResponse implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**发票编号**/
	private String idStart;

	/**发票编号**/
	private String idEnd;

	/**发票代码**/
	private String invoiceCode;

	/**发票性质：1.纸质发票 2.电子发票**/
	private String property;

	/**发票库存状态，0：可使用，1：已分发，2：已作废**/
	private String status;

	/**本数**/
	private Integer book;

	/**创建时间**/
	private Date createTime;

	/**最后修改时间**/
	private Date lastUpdate;

	/**创建人**/
	private String createUser;

	/**修改人**/
	private String updateUser;

	private List<InvoiceRepoSaveUpdBo> failList = new ArrayList<InvoiceRepoSaveUpdBo>();

	/**发票种类代码，字典表ID**/
	private String invoiceTypeCode;
	/**发票号码起**/
	private String invoiceNoStart;
	/**发票号码止**/
	private String invoiceNoEnd;
	/**每本份数**/
	private Integer share;

	private String remark;

    
    
    

	public List<InvoiceRepoSaveUpdBo> getFailList() {
		return failList;
	}
	public void setFailList(List<InvoiceRepoSaveUpdBo> failList) {
		this.failList = failList;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}

	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}

	public String getInvoiceNoStart() {
		return invoiceNoStart;
	}

	public void setInvoiceNoStart(String invoiceNoStart) {
		this.invoiceNoStart = invoiceNoStart;
	}

	public String getInvoiceNoEnd() {
		return invoiceNoEnd;
	}

	public void setInvoiceNoEnd(String invoiceNoEnd) {
		this.invoiceNoEnd = invoiceNoEnd;
	}

	public Integer getShare() {
		return share;
	}

	public void setShare(Integer share) {
		this.share = share;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}

	public String getCreateUser() {
		return createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getIdStart() {
		return idStart;
	}

	public void setIdStart(String idStart) {
		this.idStart = idStart;
	}

	public String getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(String idEnd) {
		this.idEnd = idEnd;
	}

	public InvoiceRepoBo getInvoiceRepo(){
		InvoiceRepoBo invoiceRepo = new InvoiceRepoBo();
			if(this.invoiceTypeCode!=null&&!"".equals(this.invoiceTypeCode.trim())){
				invoiceRepo.setInvoiceTypeCode(this.invoiceTypeCode);
			}
		if(this.invoiceCode!=null&&!"".equals(this.invoiceCode.trim()))
		{
			invoiceRepo.setInvoiceCode(this.invoiceCode);
		}
		if(this.property!=null&&!"".equals(this.property.trim()))
		{
			invoiceRepo.setProperty(this.property);
		}
		if(this.invoiceNoStart!=null&&!"".equals(this.invoiceNoStart.trim())) {
			invoiceRepo.setInvoiceNoStart(this.invoiceNoStart);
		}
		if(this.invoiceNoEnd!=null&&!"".equals(this.invoiceNoEnd.trim())) {
			invoiceRepo.setInvoiceNoEnd(this.invoiceNoEnd);
		}
		if(this.book!=null&&!"".equals(this.book)){
			invoiceRepo.setBook(this.book);
		}
		if(this.share!=null&&!"".equals(this.share)){
			invoiceRepo.setShare(this.share);
		}
		if(this.id!=null&&!"".equals(this.id)){

			if(this.idEnd!=null&&!"".equals(this.idEnd)){
				invoiceRepo.setId(this.id+this.idStart+"-"+this.idEnd);
			}
			else{
				invoiceRepo.setId(this.id+this.idStart+"-"+this.idStart);
			}
			//invoiceRepo.setId(this.id+this.idStart+"-"+this.idEnd);
		}
		if(this.remark!=null&&!"".equals(this.remark.trim())) {
			invoiceRepo.setRemark(this.remark);
		}
		return invoiceRepo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
