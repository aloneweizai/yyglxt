package com.abc.soa.response.system.bo;


import java.io.Serializable;
import java.util.Date;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserBehaviorStatisBO implements Serializable {
    /**PK**/
    private String id;

    /**访问次数**/
    private Integer amount;

    /**菜单名称**/
    private String menu;

    /**统计时间**/
    private java.util.Date createTime;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
