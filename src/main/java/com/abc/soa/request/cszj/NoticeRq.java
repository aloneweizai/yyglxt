package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

public class NoticeRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String id;
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
