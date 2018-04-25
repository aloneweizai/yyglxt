package com.abc.dto.cms.pub;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */
public class HotspotAskBOList  extends BaseResponse{


    private int total;

    private List<HotspotAskBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HotspotAskBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<HotspotAskBO> dataList) {
        this.dataList = dataList;
    }
}
