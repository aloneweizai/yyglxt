package com.abc.soa.request.cms;

import com.abc.common.util.PagerSpec;

/**
 * @Author liuqi
 * @Date 2017/6/6 15:15
 */
public class ModelItemCriteria {

    private String modelId;

    private Integer isChannel;

    private Integer page =0;

    private Integer size =0;

    public ModelItemCriteria withModelId(String modelId){
        this.modelId = modelId;
        return this;
    }

    public ModelItemCriteria withIsChannel(int isChannel){
        this.isChannel = isChannel;
        return this;
    }

    public ModelItemCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }

    public String getModelId() {
        return modelId;
    }

    public Integer getIsChannel() {
        return isChannel;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
