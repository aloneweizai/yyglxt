package com.abc.soa.request.consumer;
/**
 * 
 * @author zhushuai
 *
 */
public class PointsOrExpLogRq extends BaseRq{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7839870771757581980L;
	private String name;
    private String code;
    private String type;
    private String userId;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    

}
