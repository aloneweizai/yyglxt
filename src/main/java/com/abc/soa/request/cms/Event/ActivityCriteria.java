package com.abc.soa.request.cms.Event;
import com.abc.soa.request.system.BasePaginationCriteria;
import java.util.Date;

/**
 * Created by stuy on 2017/6/29.
 */
public class ActivityCriteria extends BasePaginationCriteria {


    private String title;

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

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    private String status;
    private String category;
    private Date begintime;
    private Date endtime;

}
