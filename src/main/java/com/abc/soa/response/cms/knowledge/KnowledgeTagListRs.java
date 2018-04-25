package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/8/15 11:36
 */
public class KnowledgeTagListRs extends BaseResponse{

    private List<KnowledgeTag> dataList;

    private int total;

    public int getTotal() {
        return total;
    }

    public KnowledgeTagListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<KnowledgeTag> getDataList() {
        return dataList;
    }

    public KnowledgeTagListRs setDataList(List<KnowledgeTag> dataList) {
        this.dataList = dataList;
        return this;
    }
}
