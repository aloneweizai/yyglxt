package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/20 14:40
 */
public class QuestionRs extends BaseResponse{

    private Question data;

    public Question getData() {
        return data;
    }

    public QuestionRs setData(Question data) {
        this.data = data;
        return this;
    }
}
