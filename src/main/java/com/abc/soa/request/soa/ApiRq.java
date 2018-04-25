package com.abc.soa.request.soa;



import com.abc.soa.request.consumer.BaseRq;
/**
 * API列表查询条件
 * @author  2017-11-6
 *
 */
public class ApiRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; //API名称
	private Boolean status; //api状态
	private String dictId; //字典id
	private String url; //地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
