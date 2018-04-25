package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuqi
 * @Date 2017/6/21 9:55
 */
public class QuestionnaireBORs extends BaseResponse {

    private QuestionnaireBO data;

    public QuestionnaireBO getData() {
        return data;
    }

    public void setData(QuestionnaireBO data) {
        this.data = data;
    }
}
