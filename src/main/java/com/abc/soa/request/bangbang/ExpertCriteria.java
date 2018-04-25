package com.abc.soa.request.bangbang;

/**
 * @Author liuQi
 * @Date 2017/10/23 14:11
 */
public class ExpertCriteria {

    /*用户名称*/
    private String username;

    /*真实名称*/
    private String realName;

    /*联系电话*/
    private String phone;

    /*专家类型  财税大侠，税务大侠*/
    private String type;

    /*状态*/
    private String status;

    private Integer page;

    private Integer size;

    public String getUsername() {
        return username;
    }

    public ExpertCriteria setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public ExpertCriteria setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ExpertCriteria setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ExpertCriteria setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public ExpertCriteria setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public ExpertCriteria setSize(Integer size) {
        this.size = size;
        return this;
    }

    public String getType() {
        return type;
    }

    public ExpertCriteria setType(String type) {
        this.type = type;
        return this;
    }
}
