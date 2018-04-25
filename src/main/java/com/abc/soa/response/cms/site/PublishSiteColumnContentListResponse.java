package com.abc.soa.response.cms.site;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.site.ContentsListBo;

import java.util.List;

/**
 * 生成全站时，调接口根据栏目获取属于该栏目的内容List
 * Created by zhouzhi on 2017-06-16.
 */
public class PublishSiteColumnContentListResponse extends BaseResponse {

    private List<ContentsListBo> dataList;

    public List<ContentsListBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<ContentsListBo> dataList) {
        this.dataList = dataList;
    }
}
