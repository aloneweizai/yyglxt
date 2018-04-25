package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/23 14:39
 */
public class QuestionAnswerBoRs extends BaseResponse{

    private QuestionAnswerBo data;

    public QuestionAnswerBo getData() {
        return data;
    }

    public QuestionAnswerBoRs setData(QuestionAnswerBo data) {
        this.data = data;
        return this;
    }
}
