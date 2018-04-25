package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/11/7 18:02
 */
public class QuestionClassifyStatisticsListRs extends BaseResponse{

    private int total;

    private List<QuestionClassifyStatistics> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionClassifyStatisticsListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionClassifyStatistics> getDataList() {
        return dataList;
    }

    public QuestionClassifyStatisticsListRs setDataList(List<QuestionClassifyStatistics> dataList) {
        this.dataList = dataList;
        return this;
    }
}
