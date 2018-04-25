package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by Administrator on 2017-06-13.
 */
public class CommentSaveBo extends BaseResponse {

    private CommentBo comment;

    private CommentExtBo commentExt;

    public CommentBo getComment() {
        return comment;
    }

    public void setComment(CommentBo comment) {
        this.comment = comment;
    }

    public CommentExtBo getCommentExt() {
        return commentExt;
    }

    public void setCommentExt(CommentExtBo commentExt) {
        this.commentExt = commentExt;
    }
}
