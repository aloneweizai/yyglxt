package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class CompRegisterCountBO extends TableBO implements Serializable {

    /**
     * 条数
     */
    private Integer total;
    /**
     * 时间
     */
    private String djrq;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }
}
