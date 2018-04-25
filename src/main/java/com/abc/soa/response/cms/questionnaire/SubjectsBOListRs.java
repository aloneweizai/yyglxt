package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/28 9:21
 */
public class SubjectsBOListRs extends BaseResponse {

    private List<SubjectsBO> dataList;

    public List<SubjectsBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<SubjectsBO> dataList) {
        this.dataList = dataList;
    }
}
