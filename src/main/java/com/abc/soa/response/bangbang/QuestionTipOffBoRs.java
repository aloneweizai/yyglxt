package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/13 17:37
 */
public class QuestionTipOffBoRs extends BaseResponse{

    private QuestionTipOffBo data;

    public QuestionTipOffBo getData() {
        return data;
    }

    public QuestionTipOffBoRs setData(QuestionTipOffBo data) {
        this.data = data;
        return this;
    }
}
