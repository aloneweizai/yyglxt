package com.abc.soa.response.cms.pub;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.pub.KnowledgeBase;

import java.util.List;

/**
 * Created by Administrator on 2017-08-04.
 */
public class KnowledgeBaseListResponse extends BaseResponse {
    private int total;

    private List<KnowledgeBase> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<KnowledgeBase> getDataList() {
        return dataList;
    }

    public void setDataList(List<KnowledgeBase> dataList) {
        this.dataList = dataList;
    }
}
