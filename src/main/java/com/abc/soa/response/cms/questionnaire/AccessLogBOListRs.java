package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/6 14:25
 */
public class AccessLogBOListRs extends BaseResponse{

    private List<AccessLogBO> dataList;

    public List<AccessLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AccessLogBO> dataList) {
        this.dataList = dataList;
    }
}
