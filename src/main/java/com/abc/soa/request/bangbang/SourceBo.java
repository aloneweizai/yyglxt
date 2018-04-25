package com.abc.soa.request.bangbang;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/12/18.
 */
public class SourceBo extends BaseResponse{

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
