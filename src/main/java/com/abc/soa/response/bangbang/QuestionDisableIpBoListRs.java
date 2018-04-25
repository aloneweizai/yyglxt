package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/18 18:32
 */
public class QuestionDisableIpBoListRs extends BaseResponse{

    private int total;

    private List<QuestionDisableIpBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionDisableIpBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionDisableIpBo> getDataList() {
        return dataList;
    }

    public QuestionDisableIpBoListRs setDataList(List<QuestionDisableIpBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
