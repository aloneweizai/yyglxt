package com.abc.soa.response.system;
import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Province extends BaseResponse {

	/**省ID**/
	private String provinceId;

	/**省名称**/
	private String province;



	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return this.provinceId;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return this.province;
	}

}
