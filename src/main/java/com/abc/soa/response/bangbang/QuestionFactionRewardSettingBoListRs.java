package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/28 16:11
 */
public class QuestionFactionRewardSettingBoListRs extends BaseResponse{

    private int total;

    private List<QuestionFactionRewardSettingBo> dataList;

    public int getTotal() {
        return total;
    }

    public QuestionFactionRewardSettingBoListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<QuestionFactionRewardSettingBo> getDataList() {
        return dataList;
    }

    public QuestionFactionRewardSettingBoListRs setDataList(List<QuestionFactionRewardSettingBo> dataList) {
        this.dataList = dataList;
        return this;
    }
}
