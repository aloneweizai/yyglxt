package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/27 16:41
 */
public class QuestionnaireBOListRs extends BaseResponse {

    private int total;

    private List<QuestionnaireBO> dataList;

    public List<QuestionnaireBO> getDataList() {
        return dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDataList(List<QuestionnaireBO> dataList) {
        this.dataList = dataList;
    }
}
