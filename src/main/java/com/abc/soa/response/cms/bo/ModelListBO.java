package com.abc.soa.response.cms.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by zhouzhi on 2017-05-25.
 */
public class ModelListBO extends BaseResponse {
    private int total;
    private List<ModelBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ModelBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<ModelBO> dataList) {
        this.dataList = dataList;
    }
}
