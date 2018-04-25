package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/7 14:43
 */
public class KnowledgeCategoryListRs extends BaseResponse{

    private List<KnowledgeCategory> dataList;

    private int total;

    public int getTotal() {
        return total;
    }

    public KnowledgeCategoryListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<KnowledgeCategory> getDataList() {
        return dataList;
    }

    public KnowledgeCategoryListRs setDataList(List<KnowledgeCategory> dataList) {
        this.dataList = dataList;
        return this;
    }
}
