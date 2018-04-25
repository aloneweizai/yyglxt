package com.abc.dto.cms.column;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/9.
 */
public class ChannelInsertDto extends BaseResponse{

    private ColumnDto channel;

    private ColumnExtDto channelExt;

    private List<ColumnAttrDto> channelAttrList;

    private List<ColumnGroupViewDto> groupList;

    private String operateType;

    public ColumnDto getChannel() {
        return channel;
    }

    public void setChannel(ColumnDto channel) {
        this.channel = channel;
    }

    public ColumnExtDto getChannelExt() {
        return channelExt;
    }

    public void setChannelExt(ColumnExtDto channelExt) {
        this.channelExt = channelExt;
    }

    public List<ColumnAttrDto> getChannelAttrList() {
        return channelAttrList;
    }

    public void setChannelAttrList(List<ColumnAttrDto> channelAttrList) {
        this.channelAttrList = channelAttrList;
    }

    public List<ColumnGroupViewDto> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<ColumnGroupViewDto> groupList) {
        this.groupList = groupList;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
