package com.abc.soa.request.system;

import com.abc.common.util.PagerSpec;

/**
 * @Author liuqi
 * @Date 2017/7/18 16:07
 */
public class OrgAjaxCriteria {

    private String parentId;

    private String name;

    private String type;

    private Boolean status;

    private Integer page;

    private Integer size;

    public String getParentId() {
        return parentId;
    }

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

    public OrgAjaxCriteria setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public OrgAjaxCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public OrgAjaxCriteria setType(String type) {
        this.type = type;
        return this;
    }

    public OrgAjaxCriteria setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public OrgAjaxCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }
}
