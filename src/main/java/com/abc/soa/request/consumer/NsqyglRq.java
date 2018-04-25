package com.abc.soa.request.consumer;
/**
 * 纳税人信息查询
 * @author zhushuai 2017-6-19
 *
 */
public class NsqyglRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//纳税人识别号
    private String nsrsbh;
    
    private String p;

	private String api;

	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}
}
