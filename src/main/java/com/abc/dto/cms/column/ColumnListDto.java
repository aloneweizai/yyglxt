package com.abc.dto.cms.column;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/6.
 */
public class ColumnListDto extends BaseResponse implements java.io.Serializable{

    List<ColumnDto> dataList;

    public List<ColumnDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<ColumnDto> dataList) {
        this.dataList = dataList;
    }
}
