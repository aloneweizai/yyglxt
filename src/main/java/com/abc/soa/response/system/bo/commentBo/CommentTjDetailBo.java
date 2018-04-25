package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by Administrator on 2017-06-20.
 */
public class CommentTjDetailBo extends BaseResponse {

    private CommentTjListBo data;

    public CommentTjListBo getData() {
        return data;
    }

    public void setData(CommentTjListBo data) {
        this.data = data;
    }
}
