package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.TemplateBO;

import java.util.List;

public class WxTemplateRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<TemplateBO> dataList;
    private TemplateBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<TemplateBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<TemplateBO> dataList) {
        this.dataList = dataList;
    }

    public TemplateBO getData() {
        return data;
    }

    public void setData(TemplateBO data) {
        this.data = data;
    }
}
