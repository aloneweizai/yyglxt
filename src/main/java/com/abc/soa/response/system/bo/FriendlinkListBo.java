package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Friendlink;

import java.util.List;

/**
 * Created by Administrator on 2017-06-06.
 */
public class FriendlinkListBo extends BaseResponse {

    private List<Friendlink> dataList; //友情链接列表

    private int total; //总记录数

    public List<Friendlink> getDataList() {
        return dataList;
    }

    public void setDataList(List<Friendlink> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
