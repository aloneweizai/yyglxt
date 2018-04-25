package com.abc.soa.response.region;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:23
 */
public class RegionRs extends BaseResponse {

    private List<Region> dataList;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Region> getDataList() {
        return dataList;
    }

    public void setDataList(List<Region> dataList) {
        this.dataList = dataList;
    }
}
