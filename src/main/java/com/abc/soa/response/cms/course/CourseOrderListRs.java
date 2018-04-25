package com.abc.soa.response.cms.course;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.Order;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/4 11:21
 */
public class CourseOrderListRs extends BaseResponse{

    private List<Order> dataList;

    private int total;

    public List<Order> getDataList() {
        return dataList;
    }

    public CourseOrderListRs setDataList(List<Order> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public CourseOrderListRs setTotal(int total) {
        this.total = total;
        return this;
    }
}
