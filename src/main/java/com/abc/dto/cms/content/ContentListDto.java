package com.abc.dto.cms.content;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class ContentListDto extends BaseResponse{

    private String total;

    private List<ContentViewDto> dataList;

    public List<ContentViewDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContentViewDto> dataList) {
        this.dataList = dataList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
