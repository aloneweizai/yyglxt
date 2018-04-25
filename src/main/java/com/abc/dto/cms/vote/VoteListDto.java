package com.abc.dto.cms.vote;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/19.
 */
public class VoteListDto extends BaseResponse{

    private long total;

    private List<VoteDto> dataList;

    public List<VoteDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<VoteDto> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
