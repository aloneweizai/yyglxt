package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by wuhao on 2017-05-24.
 */
public class DictListBO extends BaseResponse {

    private int total;

    private List<DictBO> dataList;

    private DictBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DictBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<DictBO> dataList) {
        this.dataList = dataList;
    }

    public DictBO getData() {
        return data;
    }

    public void setData(DictBO data) {
        this.data = data;
    }
}
