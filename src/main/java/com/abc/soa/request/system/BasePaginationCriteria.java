package com.abc.soa.request.system;

import org.springframework.util.StringUtils;

/**
 * Created by relic5 on 2017/6/6.
 */
public class BasePaginationCriteria {

    private static final long serialVersionUID = 1L;

    private Integer page = new Integer(1);

    private Integer size = new Integer(15);

    private Long totalItems;    //共多少条

    private Integer totalPage;      //总页数

    public BasePaginationCriteria(){}

    public BasePaginationCriteria(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
