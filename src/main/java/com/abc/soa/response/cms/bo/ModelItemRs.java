package com.abc.soa.response.cms.bo;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/6/16 9:24
 */
public class ModelItemRs extends BaseResponse {

    private ModelItemBo data;

    public ModelItemBo getData() {
        return data;
    }

    public void setData(ModelItemBo data) {
        this.data = data;
    }
}
