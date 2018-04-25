package com.abc.soa.response.cms.staticpage;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.staticpage.ContentPreNextBo;

import java.util.List;

/**
 * 获取内容的前后各一条记录
 * Created by zhouzhi on 2017-07-12.
 */
public class ContentPreNextResponse extends BaseResponse {
    private List<ContentPreNextBo> dataList;

    public List<ContentPreNextBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContentPreNextBo> dataList) {
        this.dataList = dataList;
    }
}
