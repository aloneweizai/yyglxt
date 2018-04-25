package com.abc.dto.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8 0008.
 */
public class KnowledgeTagList extends BaseResponse {


    private String total;

    private List<KnowledgeTag> dataList;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<KnowledgeTag> getDataList() {
        return dataList;
    }

    public void setDataList(List<KnowledgeTag> dataList) {
        this.dataList = dataList;
    }
}
