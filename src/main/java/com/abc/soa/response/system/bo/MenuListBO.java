package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.system.Menu;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/5/16 9:45
 */
public class MenuListBO extends BaseResponse{

    private int total;

    private List<Menu> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Menu> getDataList() {
        return dataList;
    }

    public void setDataList(List<Menu> dataList) {
        this.dataList = dataList;
    }
}
