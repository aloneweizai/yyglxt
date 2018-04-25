package com.abc.soa.response.cms.pub;

import com.abc.common.soa.response.BaseResponse;
import com.abc.dto.cms.pub.CurriculumListsyBo;

import java.util.List;

/**
 * Created by Administrator on 2017-08-23.
 */
public class CurriculumListsyResponse extends BaseResponse {
    private List<CurriculumListsyBo> dataList;

    public List<CurriculumListsyBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CurriculumListsyBo> dataList) {
        this.dataList = dataList;
    }
}
