package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.WxMenu;

import java.util.List;

public class WxMenuRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private WxMenu data;


    public WxMenu getData() {
        return data;
    }

    public void setData(WxMenu data) {
        this.data = data;
    }

}
