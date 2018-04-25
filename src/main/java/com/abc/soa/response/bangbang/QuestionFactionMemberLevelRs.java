package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/10/25 09:47
 */
public class QuestionFactionMemberLevelRs extends BaseResponse{

    private QuestionFactionMemberLevel data;

    public QuestionFactionMemberLevel getData() {
        return data;
    }

    public QuestionFactionMemberLevelRs setData(QuestionFactionMemberLevel data) {
        this.data = data;
        return this;
    }
}
