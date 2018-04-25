package com.abc.soa.response.cms.tpl;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.tpl.TemplateBo;

import java.util.List;

/**
 * Created by Administrator on 2017-07-06.
 */
public class TplListResponse extends BaseResponse{
    private List<TemplateBo> dataList;
    private String total;

    public List<TemplateBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<TemplateBo> dataList) {
        this.dataList = dataList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
