package com.abc.soa.response.cms.questionnaire;


import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/14 15:24
 */
public class ImageListRs extends BaseResponse {

    private List<Images> dataList;

    public List<Images> getDataList() {
        return dataList;
    }

    public void setDataList(List<Images> dataList) {
        this.dataList = dataList;
    }
}
