package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Menu;

/**
 * @Author liuqi
 * @Date 2017/6/13 16:27
 */
public class MenuRs extends BaseResponse{

    private Menu data;

    public Menu getData() {
        return data;
    }

    public void setData(Menu data) {
        this.data = data;
    }
}
