package com.abc.dto.cms.column;

import java.util.List;

/**
 * Created by relic5 on 2017/6/7.
 */
public class ChannelItemListDto {

    private List<ChannelItemDto> modelItems;

    private String tplPrefix;

    public List<ChannelItemDto> getModelItems() {
        return modelItems;
    }

    public void setModelItems(List<ChannelItemDto> modelItems) {
        this.modelItems = modelItems;
    }

    public String getTplPrefix() {
        return tplPrefix;
    }

    public void setTplPrefix(String tplPrefix) {
        this.tplPrefix = tplPrefix;
    }
}
