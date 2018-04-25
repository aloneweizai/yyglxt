package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserAreaStatisRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String start;//开始时间
	private String end;//结束时间
	private String type;//区域类型，country、province
	private String province;
    private String tagName;
	private String timeInterval;
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
