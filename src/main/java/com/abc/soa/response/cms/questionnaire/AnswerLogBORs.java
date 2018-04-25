package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/7/5 15:22
 */
public class AnswerLogBORs extends BaseResponse{

    private AnswerLogBO data;

    public AnswerLogBO getData() {
        return data;
    }

    public void setData(AnswerLogBO data) {
        this.data = data;
    }
}
