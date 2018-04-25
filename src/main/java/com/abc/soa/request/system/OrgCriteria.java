package com.abc.soa.request.system;

import com.abc.common.util.PagerSpec;

/**
 * 组织查询参数
 * @Author liuqi
 * @Date 2017/5/22 15:18
 */
public class OrgCriteria {

    private String name;

    private String type;

    private Boolean status;

    private Integer page;

    private Integer size;

    public String getName() {
        return name;
    }

    public String getType(){
        return type;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public OrgCriteria setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public OrgCriteria setType(String type) {
        this.type = type;
        return this;
    }

    public OrgCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public OrgCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }
}
