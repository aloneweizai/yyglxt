package com.abc.soa.response.cms.site;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.column.ChannelInsertDto;

import java.util.List;

/**
 * Created by zhouzhi on 2017-06-13.
 */
public class PublishSiteColumnListResponse extends BaseResponse{
    private List<ChannelInsertDto> dataList;

    public List<ChannelInsertDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<ChannelInsertDto> dataList) {
        this.dataList = dataList;
    }
}
