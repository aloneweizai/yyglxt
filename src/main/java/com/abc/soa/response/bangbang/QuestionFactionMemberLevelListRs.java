package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/25 09:43
 */
public class QuestionFactionMemberLevelListRs extends BaseResponse{

    private int total;

    private List<QuestionFactionMemberLevel> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionFactionMemberLevelListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionFactionMemberLevel> getDataList() {
        return dataList;
    }

    public QuestionFactionMemberLevelListRs setDataList(List<QuestionFactionMemberLevel> dataList) {
        this.dataList = dataList;
        return this;
    }
}
