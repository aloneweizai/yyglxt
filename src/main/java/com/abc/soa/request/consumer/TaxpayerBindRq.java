package com.abc.soa.request.consumer;
/**
 * 纳税人信息查询
 * @author zhushuai 2017-6-19
 *
 */
public class TaxpayerBindRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String username;
    private String nsrsbh;
    private Boolean status;
    private String type;
    
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
