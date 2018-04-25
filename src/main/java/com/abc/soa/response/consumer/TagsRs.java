package com.abc.soa.response.consumer;

import java.util.List;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.consumer.bo.Tag;

public class TagsRs extends BaseResponse{
	private static final long serialVersionUID = 1L;
	private int total;

    private List<Tag> dataList;
    private Tag data;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Tag> getDataList() {
		return dataList;
	}
	public void setDataList(List<Tag> dataList) {
		this.dataList = dataList;
	}
	public Tag getData() {
		return data;
	}
	public void setData(Tag data) {
		this.data = data;
	}
    
    
}
