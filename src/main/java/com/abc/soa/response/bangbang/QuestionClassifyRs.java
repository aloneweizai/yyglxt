package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/12 17:22
 */
public class QuestionClassifyRs extends BaseResponse{

    private QuestionClassify data;

    public QuestionClassify getData() {
        return data;
    }

    public QuestionClassifyRs setData(QuestionClassify data) {
        this.data = data;
        return this;
    }
}
