package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/12 17:20
 */
public class QuestionClassifyListRs extends BaseResponse{

    private int total;

    private List<QuestionClassify> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionClassifyListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionClassify> getDataList() {
        return dataList;
    }

    public QuestionClassifyListRs setDataList(List<QuestionClassify> dataList) {
        this.dataList = dataList;
        return this;
    }
}
