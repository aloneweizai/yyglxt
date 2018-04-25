package com.abc.soa.request.bangbang;

import org.apache.commons.lang.StringUtils;

/**
 * @Author liuQi
 * @Date 2017/10/28 16:20
 */
public class FactionRewardSettingCriteria {

    private Integer page;

    private Integer size;

    /**/
    private String factionName;

    /**/
    private Boolean status;

    /**/
    private String date;

    /**/
    private Integer factionNumOfMonth;

    public String getFactionName() {
        return factionName;
    }

    public FactionRewardSettingCriteria setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public FactionRewardSettingCriteria setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getDate() {
        return date;
    }

    public FactionRewardSettingCriteria setDate(String date) {
        if(!StringUtils.isEmpty(date)){
            date = date.replace("-","");
        }
        this.date = date;
        return this;
    }


    public Integer getFactionNumOfMonth() {
        return factionNumOfMonth;
    }

    public FactionRewardSettingCriteria setFactionNumOfMonth(Integer factionNumOfMonth) {
        this.factionNumOfMonth = factionNumOfMonth;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public FactionRewardSettingCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public FactionRewardSettingCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }
}
