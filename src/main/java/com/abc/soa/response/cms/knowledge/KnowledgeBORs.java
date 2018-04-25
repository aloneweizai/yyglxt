package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/9/7 14:31
 */
public class KnowledgeBORs extends BaseResponse{

    private KnowledgeBO data;

    public KnowledgeBO getData() {
        return data;
    }

    public KnowledgeBORs setData(KnowledgeBO data) {
        this.data = data;
        return this;
    }
}
