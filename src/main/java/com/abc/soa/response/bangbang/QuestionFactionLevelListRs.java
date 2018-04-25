package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/25 09:43
 */
public class QuestionFactionLevelListRs extends BaseResponse{

    private int total;

    private List<QuestionFactionLevel> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionFactionLevelListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionFactionLevel> getDataList() {
        return dataList;
    }

    public QuestionFactionLevelListRs setDataList(List<QuestionFactionLevel> dataList) {
        this.dataList = dataList;
        return this;
    }
}
