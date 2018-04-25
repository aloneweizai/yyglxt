package com.abc.dto.cms.pub;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */
public class AskBOList extends BaseResponse{

    private int total;


    private List<AskBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AskBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AskBO> dataList) {
        this.dataList = dataList;
    }
}
