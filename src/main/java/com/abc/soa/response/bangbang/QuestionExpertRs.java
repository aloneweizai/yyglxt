package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/19 20:38
 */
public class QuestionExpertRs extends BaseResponse{

    private QuestionExpert data;

    public QuestionExpert getData() {
        return data;
    }

    public QuestionExpertRs setData(QuestionExpert data) {
        this.data = data;
        return this;
    }
}
