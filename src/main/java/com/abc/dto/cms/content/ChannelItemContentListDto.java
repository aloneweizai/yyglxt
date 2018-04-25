package com.abc.dto.cms.content;

import com.abc.dto.cms.column.ChannelItemDto;
import com.abc.dto.cms.column.ColumnDto;

import java.util.List;

/**
 * Created by relic5 on 2017/6/7.
 */
public class ChannelItemContentListDto {

    private List<ChannelItemDto> modelItems;

    private List<ColumnDto> channels;

    private String tplPrefix;

    public List<ChannelItemDto> getModelItems() {
        return modelItems;
    }

    public void setModelItems(List<ChannelItemDto> modelItems) {
        this.modelItems = modelItems;
    }

    public List<ColumnDto> getChannels() {
        return channels;
    }

    public void setChannels(List<ColumnDto> channels) {
        this.channels = channels;
    }

    public String getTplPrefix() {
        return tplPrefix;
    }

    public void setTplPrefix(String tplPrefix) {
        this.tplPrefix = tplPrefix;
    }
}
