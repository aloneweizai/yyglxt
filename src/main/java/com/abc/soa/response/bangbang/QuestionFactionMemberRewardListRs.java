package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/31 18:39
 */
public class QuestionFactionMemberRewardListRs extends BaseResponse{

    private int total;

    private List<QuestionFactionMemberReward> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionFactionMemberRewardListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionFactionMemberReward> getDataList() {
        return dataList;
    }

    public QuestionFactionMemberRewardListRs setDataList(List<QuestionFactionMemberReward> dataList) {
        this.dataList = dataList;
        return this;
    }
}
