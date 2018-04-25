package com.abc.soa.response.system.bo.commentBo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cms.file.FileBo;

import java.util.List;

/**
 * Created by Administrator on 2017-06-13.
 */
public class CommentFileListBo extends BaseResponse{
    private List<FileBo> dataList;

    public List<FileBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<FileBo> dataList) {
        this.dataList = dataList;
    }

}
