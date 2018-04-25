package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by Administrator on 2017-06-13.
 */
public class CommentItemBo extends BaseResponse {

    private CommentBo comment;

    public CommentBo getComment() {
        return comment;
    }

    public void setComment(CommentBo comment) {
        this.comment = comment;
    }
}