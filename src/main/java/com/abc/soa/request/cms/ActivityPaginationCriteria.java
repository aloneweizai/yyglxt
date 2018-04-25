package com.abc.soa.request.cms;

import com.abc.soa.request.system.BasePaginationCriteria;

import java.util.Date;

/**
 * Created by stuy on 2017/7/10.
 */
public class ActivityPaginationCriteria extends BasePaginationCriteria {
    private String title="";
    private String status="";
    private String category="";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    private String begintime="";
    private String endtime="";

}
