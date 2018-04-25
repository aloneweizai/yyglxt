package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/25 09:47
 */
public class QuestionFactionLevelRs extends BaseResponse{

    private QuestionFactionLevel data;

    public QuestionFactionLevel getData() {
        return data;
    }

    public QuestionFactionLevelRs setData(QuestionFactionLevel data) {
        this.data = data;
        return this;
    }
}
