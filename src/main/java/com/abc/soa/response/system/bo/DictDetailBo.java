package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Dict;

/**
 * Created by wuhao on 2017-06-20.
 */
public class DictDetailBo extends BaseResponse {
    private Dict data;

    public Dict getData() {
        return data;
    }

    public void setData(Dict data) {
        this.data = data;
    }
}
