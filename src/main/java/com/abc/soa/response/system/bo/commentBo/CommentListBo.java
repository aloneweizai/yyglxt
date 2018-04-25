package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-06-13.
 */
public class CommentListBo extends BaseResponse {

    private int total;

    private List<CommentBo> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CommentBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CommentBo> dataList) {
        this.dataList = dataList;
    }
}
