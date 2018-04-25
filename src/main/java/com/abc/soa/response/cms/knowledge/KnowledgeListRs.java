package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/7 14:38
 */
public class KnowledgeListRs extends BaseResponse{

    private List<Knowledge> dataList;

    private int total;

    public List<Knowledge> getDataList() {
        return dataList;
    }

    public KnowledgeListRs setDataList(List<Knowledge> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public KnowledgeListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
