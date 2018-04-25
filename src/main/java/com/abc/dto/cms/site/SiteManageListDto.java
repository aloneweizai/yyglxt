package com.abc.dto.cms.site;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/6.
 */
public class SiteManageListDto extends BaseResponse{

    private List<SiteManageDto> dataList;

    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<SiteManageDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<SiteManageDto> dataList) {
        this.dataList = dataList;
    }

}
