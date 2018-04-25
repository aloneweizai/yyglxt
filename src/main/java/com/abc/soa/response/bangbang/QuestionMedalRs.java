package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/20 18:37
 */
public class QuestionMedalRs extends BaseResponse{

    private QuestionMedal data;

    public QuestionMedal getData() {
        return data;
    }

    public QuestionMedalRs setData(QuestionMedal data) {
        this.data = data;
        return this;
    }
}
