package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/11/23 14:47
 */
public class CheatsCommentBoRs extends BaseResponse{

    private CheatsCommentBo data;

    public CheatsCommentBo getData() {
        return data;
    }

    public CheatsCommentBoRs setData(CheatsCommentBo data) {
        this.data = data;
        return this;
    }
}
