package com.abc.soa.response.consumer.bo;

public class ExpCodex {
	
    private String uexpruleId;//经验值规则ID
    private String clientType;//客户端类型
    private Integer uexp; //经验值
    private String period;//周期 A:无周期,Y:年,M:月,D:日, H:小时
    private Integer degree;//周期内次数,0无限次
    private String remark;//备注
    
	
    
	public String getUexpruleId() {
		return uexpruleId;
	}
	public void setUexpruleId(String uexpruleId) {
		this.uexpruleId = uexpruleId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public Integer getUexp() {
		return uexp;
	}
	public void setUexp(Integer uexp) {
		this.uexp = uexp;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}
