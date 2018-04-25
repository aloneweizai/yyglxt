package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;


public class WeiXinCode extends BaseResponse implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String nonce_str;


	private String code_url;

	private String mweb_url;

	private String appid;

	private String sign;

	private String trade_type;

	private String code_img;

	private String sign_type;

	private String prepay_id;

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getMweb_url() {
		return mweb_url;
	}

	public void setMweb_url(String mweb_url) {
		this.mweb_url = mweb_url;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getCode_img() {
		return code_img;
	}

	public void setCode_img(String code_img) {
		this.code_img = code_img;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
}
