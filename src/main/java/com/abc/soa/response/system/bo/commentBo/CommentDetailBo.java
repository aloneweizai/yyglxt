package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by Administrator on 2017-06-20.
 */
public class CommentDetailBo extends BaseResponse {

    private CommentSaveBo data;

    public CommentSaveBo getData() {
        return data;
    }

    public void setData(CommentSaveBo data) {
        this.data = data;
    }
}
