package com.abc.soa.response.cms.tpl;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.tpl.TemplateBo;

/**
 * Created by zhouzhi on 2017-07-11.
 */
public class TplResponse extends BaseResponse{
    private TemplateBo data;

    public TemplateBo getData() {
        return data;
    }

    public void setData(TemplateBo data) {
        this.data = data;
    }
}
