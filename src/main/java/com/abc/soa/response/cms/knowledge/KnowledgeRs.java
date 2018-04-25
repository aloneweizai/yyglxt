package com.abc.soa.response.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/9/7 14:27
 */
public class KnowledgeRs extends BaseResponse {

    private Knowledge data;

    public Knowledge getData() {
        return data;
    }

    public KnowledgeRs setData(Knowledge data) {
        this.data = data;
        return this;
    }
}
