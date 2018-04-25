package com.abc.soa.request.system;

import com.abc.common.util.PagerSpec;

/**
 * @Author liuqi
 * @Date 2017/7/19 09:34
 */
public class MenuAjaxCriteria {

    private String menuName;

    private String parentId;

    private String type;

    private Integer page;

    private Integer size;

    public MenuAjaxCriteria withMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public MenuAjaxCriteria withParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public MenuAjaxCriteria withType(String type) {
        this.type = type;
        return this;
    }

    public MenuAjaxCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public String getType() {
        return type;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
