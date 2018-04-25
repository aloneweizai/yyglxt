package com.abc.soa.response.cms.pub;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.pub.AdPageBO;

import java.util.List;

/**
 * Created by Administrator on 2017-08-23.
 */
public class AdPageResponse extends BaseResponse {
    private List<AdPageBO> dataList;

    public List<AdPageBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AdPageBO> dataList) {
        this.dataList = dataList;
    }
}
