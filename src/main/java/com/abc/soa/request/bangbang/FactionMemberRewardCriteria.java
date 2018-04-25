package com.abc.soa.request.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/31 18:36
 */
public class FactionMemberRewardCriteria {

    private Integer page;

    private Integer size;

    /*帮派名称*/
    private String factionName;

    /*状态*/
    private String status;

    public Integer getPage() {
        return page;
    }

    public FactionMemberRewardCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public FactionMemberRewardCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public FactionMemberRewardCriteria setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FactionMemberRewardCriteria setStatus(String status) {
        this.status = status;
        return this;
    }
}
