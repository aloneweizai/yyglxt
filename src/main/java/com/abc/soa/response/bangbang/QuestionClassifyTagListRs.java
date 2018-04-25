package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/12 17:29
 */
public class QuestionClassifyTagListRs extends BaseResponse{

    private int total;

    private List<QuestionClassifyTag> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionClassifyTagListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionClassifyTag> getDataList() {
        return dataList;
    }

    public QuestionClassifyTagListRs setDataList(List<QuestionClassifyTag> dataList) {
        this.dataList = dataList;
        return this;
    }
}
