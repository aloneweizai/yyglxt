package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/16 17:53
 */
public class QuestionHeadmanBoRs extends BaseResponse{

    private QuestionHeadmanBo data;

    public QuestionHeadmanBo getData() {
        return data;
    }

    public QuestionHeadmanBoRs setData(QuestionHeadmanBo data) {
        this.data = data;
        return this;
    }
}
