package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/23 14:44
 */
public class QuestionCommentBoRs extends BaseResponse{

    private QuestionCommentBo data;

    public QuestionCommentBo getData() {
        return data;
    }

    public QuestionCommentBoRs setData(QuestionCommentBo data) {
        this.data = data;
        return this;
    }
}
