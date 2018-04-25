package com.abc.soa.response.cms.questionnaire;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/27 16:42
 */
public class AnswertjBOListRs extends BaseResponse{

    private int total;

    private List<AnswertjBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AnswertjBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<AnswertjBO> dataList) {
        this.dataList = dataList;
    }
}
