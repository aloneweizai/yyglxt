package com.abc.soa.request.cms.task;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/5.
 */
public class TaskDataBo extends BaseResponse {
    private TaskBo data;

    public TaskBo getData() {
        return data;
    }

    public void setData(TaskBo data) {
        this.data = data;
    }
}
