package com.abc.soa.response.cms.bo;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/6/16 9:22
 */
public class ModelRs extends BaseResponse{

    private ModelBO data;

    public ModelBO getData() {
        return data;
    }

    public void setData(ModelBO data) {
        this.data = data;
    }
}
