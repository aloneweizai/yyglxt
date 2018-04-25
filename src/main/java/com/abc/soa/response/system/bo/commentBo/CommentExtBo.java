package com.abc.soa.response.system.bo.commentBo;

/**
 * Created by Administrator on 2017-06-13.
 */
public class CommentExtBo {
    private String commentId;//commentId
    private String ip;//IP地址
    private String text;//评论内容
    private String reply;//回复内容

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
