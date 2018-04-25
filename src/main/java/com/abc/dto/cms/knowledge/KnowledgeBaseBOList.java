package com.abc.dto.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class KnowledgeBaseBOList extends BaseResponse {

    private int total;

    private List<Knowledge> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Knowledge> getDataList() {
        return dataList;
    }

    public void setDataList(List<Knowledge> dataList) {
        this.dataList = dataList;
    }
}
