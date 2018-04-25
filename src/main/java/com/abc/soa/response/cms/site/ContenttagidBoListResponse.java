package com.abc.soa.response.cms.site;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.site.ContenttagidBo;

import java.util.List;

/**
 * Created by Administrator on 2017-09-05.
 */
public class ContenttagidBoListResponse extends BaseResponse {
    private List<ContenttagidBo> dataList;

    public List<ContenttagidBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContenttagidBo> dataList) {
        this.dataList = dataList;
    }
}
