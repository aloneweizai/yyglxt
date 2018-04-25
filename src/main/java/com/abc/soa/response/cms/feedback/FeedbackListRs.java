package com.abc.soa.response.cms.feedback;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/12 11:40
 */
public class FeedbackListRs extends BaseResponse {

    private List<Feedback> dataList;

    private int total;

    public List<Feedback> getDataList() {
        return dataList;
    }

    public void setDataList(List<Feedback> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
