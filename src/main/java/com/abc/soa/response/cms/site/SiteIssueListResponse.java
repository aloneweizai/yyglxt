package com.abc.soa.response.cms.site;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.site.SiteIssueBo;

import java.util.List;

/**
 * Created by zhouzhi on 2017-06-12.
 */
public class SiteIssueListResponse  extends BaseResponse{
    private List<SiteIssueBo> dataList;

    private String total;

    public List<SiteIssueBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<SiteIssueBo> dataList) {
        this.dataList = dataList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
