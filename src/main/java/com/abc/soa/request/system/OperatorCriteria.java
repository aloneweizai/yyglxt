package com.abc.soa.request.system;

import com.abc.common.util.PagerSpec;

/**
 * @Author liuqi
 * @Date 2017/5/19 14:22
 */
public class OperatorCriteria {

    private String orgId;

    private String username;

    private String nickname;

    private String phone;

    private Boolean status;

    private Integer page;

    private Integer size;

    public String getOrgId() {
        return orgId;
    }

    public Integer getPage() {
        return page;
    }

    public String getUsername() {
        return username;
    }

    public Integer getSize() {
        return size;
    }

    public OperatorCriteria setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public OperatorCriteria setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public OperatorCriteria setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public OperatorCriteria setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public OperatorCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public OperatorCriteria setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
