package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/17 17:30
 */
public class QuestionSysBlockBoListRs extends BaseResponse{

    private int total;

    private List<QuestionSysBlockBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionSysBlockBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionSysBlockBo> getDataList() {
        return dataList;
    }

    public QuestionSysBlockBoListRs setDataList(List<QuestionSysBlockBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
