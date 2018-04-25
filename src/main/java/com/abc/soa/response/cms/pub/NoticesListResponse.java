package com.abc.soa.response.cms.pub;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.NoticeBO;

import java.util.List;

/**
 * Created by Administrator on 2017-08-23.
 */
public class NoticesListResponse extends BaseResponse {
    private List<NoticeBO> dataList;

    public List<NoticeBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<NoticeBO> dataList) {
        this.dataList = dataList;
    }
}
