package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/11/10 19:30
 */
public class RecommendBoListRs extends BaseResponse{

    private int total;

    private List<RecommendBo> dataList;

    public int getTotal() {
        return total;
    }

    public RecommendBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<RecommendBo> getDataList() {
        return dataList;
    }

    public RecommendBoListRs setDataList(List<RecommendBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
