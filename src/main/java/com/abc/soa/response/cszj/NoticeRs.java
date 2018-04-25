package com.abc.soa.response.cszj;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cszj.bo.NoticeBO;

import java.util.List;

public class NoticeRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<NoticeBO> dataList;
    private NoticeBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<NoticeBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<NoticeBO> dataList) {
        this.dataList = dataList;
    }

    public NoticeBO getData() {
        return data;
    }

    public void setData(NoticeBO data) {
        this.data = data;
    }
}
