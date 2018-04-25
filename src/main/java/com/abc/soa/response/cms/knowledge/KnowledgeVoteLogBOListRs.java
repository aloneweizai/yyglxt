package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/10 16:55
 */
public class KnowledgeVoteLogBOListRs extends BaseResponse {

    private List<KnowledgeVoteLogBO> dataList;

    private int total;

    public List<KnowledgeVoteLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<KnowledgeVoteLogBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
