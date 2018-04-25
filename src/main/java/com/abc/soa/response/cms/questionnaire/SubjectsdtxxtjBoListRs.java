package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.Base64;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/13 16:19
 */
public class SubjectsdtxxtjBoListRs extends BaseResponse{

    private List<SubjectsdtxxtjBo> dataList;

    public List<SubjectsdtxxtjBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<SubjectsdtxxtjBo> dataList) {
        this.dataList = dataList;
    }
}
