package com.abc.soa.request.cms.bo;
import com.abc.soa.response.cms.bo.ModelItemBo;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * CMS模型项表
 * 
 **/
@SuppressWarnings("serial")
public class ModelItemListReq{

	private List<ModelItemBo> modelItemBoList;

	public List<ModelItemBo> getModelItemBoList() {
		return modelItemBoList;
	}

	public void setModelItemBoList(List<ModelItemBo> modelItemBoList) {
		this.modelItemBoList = modelItemBoList;
	}
}
