package com.abc.soa.response.region;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Region extends BaseResponse {

	/**地区ID**/
	private String regionId;

	/**地区名称**/
	private String regionName;

	/**市ID**/
	private String pId;


	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
}
