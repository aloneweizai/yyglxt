package com.abc.soa.response.cms.bo;
import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;
import java.util.List;


/**
 * CMS模型项集合
 **/
@SuppressWarnings("serial")
public class ModelItemListBo extends BaseResponse {

	private int total;

	private List<ModelItemBo> dataList;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<ModelItemBo> getDataList() {
		return dataList;
	}

	public void setDataList(List<ModelItemBo> dataList) {
		this.dataList = dataList;
	}
}
