package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/18 18:31
 */
public class QuestionDisableUserBoListRs extends BaseResponse{

    private int total;

    private List<QuestionDisableUserBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionDisableUserBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionDisableUserBo> getDataList() {
        return dataList;
    }

    public QuestionDisableUserBoListRs setDataList(List<QuestionDisableUserBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
