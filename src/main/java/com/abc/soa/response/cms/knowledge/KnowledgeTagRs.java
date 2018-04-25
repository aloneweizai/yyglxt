package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/8/15 11:30
 */
public class KnowledgeTagRs extends BaseResponse {

    private KnowledgeTag data;

    public KnowledgeTag getData() {
        return data;
    }

    public KnowledgeTagRs setData(KnowledgeTag data) {
        this.data = data;
        return this;
    }
}
