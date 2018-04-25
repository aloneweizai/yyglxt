package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/6 14:50
 */
public class AnswerLogBOListRs extends BaseResponse{

    private int total;

    private List<AnswerLogBO> dataList;

    public List<AnswerLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AnswerLogBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
