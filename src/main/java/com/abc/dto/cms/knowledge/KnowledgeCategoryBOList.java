package com.abc.dto.cms.knowledge;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class KnowledgeCategoryBOList extends BaseResponse{

    private String code;

    private String message;

    private KnowledgeCategoryBO data;

    private List<KnowledgeCategoryBO> dataList;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public KnowledgeCategoryBO getData() {
        return data;
    }

    public void setData(KnowledgeCategoryBO data) {
        this.data = data;
    }

    public List<KnowledgeCategoryBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<KnowledgeCategoryBO> dataList) {
        this.dataList = dataList;
    }
}
