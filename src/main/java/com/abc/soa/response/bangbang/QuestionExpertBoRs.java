package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/20 16:30
 */
public class QuestionExpertBoRs extends BaseResponse{

    private QuestionExpertBo data;

    public QuestionExpertBo getData() {
        return data;
    }

    public QuestionExpertBoRs setData(QuestionExpertBo data) {
        this.data = data;
        return this;
    }
}
