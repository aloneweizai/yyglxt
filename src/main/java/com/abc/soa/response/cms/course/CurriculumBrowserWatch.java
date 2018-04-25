package com.abc.soa.response.cms.course;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2018/1/30 20:46
 */
public class CurriculumBrowserWatch {

    /**ID**varchar(64)**/
    private String id;

    /**?????????**tinyint(11)**/
    private Integer browseNum;

    /**?????????**tinyint(11)**/
    private Integer watchNum;

    /**??????**datetime**/
    private java.util.Date date;

    public String getId() {
        return id;
    }

    public CurriculumBrowserWatch setId(String id) {
        this.id = id;
        return this;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public CurriculumBrowserWatch setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
        return this;
    }

    public Integer getWatchNum() {
        return watchNum;
    }

    public CurriculumBrowserWatch setWatchNum(Integer watchNum) {
        this.watchNum = watchNum;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public CurriculumBrowserWatch setDate(Date date) {
        this.date = date;
        return this;
    }
}
