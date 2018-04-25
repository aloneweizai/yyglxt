package com.abc.soa.response.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/23 10:26
 */
public class SensitiveWordsListRs extends BaseResponse{

    private int total;

    private List<SensitiveWords> dataList;

    public int getTotal() {
        return total;
    }

    public SensitiveWordsListRs setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<SensitiveWords> getDataList() {
        return dataList;
    }

    public SensitiveWordsListRs setDataList(List<SensitiveWords> dataList) {
        this.dataList = dataList;
        return this;
    }
}
