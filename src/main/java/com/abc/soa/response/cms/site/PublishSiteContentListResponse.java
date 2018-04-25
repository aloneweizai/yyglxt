package com.abc.soa.response.cms.site;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.site.ContentSaveBo;

import java.util.List;

/**
 * Created by zhouzhi on 2017-06-13.
 */
public class PublishSiteContentListResponse extends BaseResponse{
    private List<ContentSaveBo> dataList;

    public List<ContentSaveBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContentSaveBo> dataList) {
        this.dataList = dataList;
    }
}
