package com.abc.dto.cms.topic;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/17.
 */
public class TopicListWrapper extends BaseResponse{

    private List<TopicDto> dataList;

    private Integer total;

    public List<TopicDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<TopicDto> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
