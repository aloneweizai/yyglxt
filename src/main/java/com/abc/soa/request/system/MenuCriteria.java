package com.abc.soa.request.system;


import com.abc.common.util.PagerSpec;

/**
 * 菜单查询参数
 * @Author liuqi
 * @Date 2017/5/17 15:45
 */
public class MenuCriteria  {

    private String menuName;

    private String type;

    private Integer page;

    private Integer size;

    public MenuCriteria withMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public MenuCriteria withType(String type) {
        this.type = type;
        return this;
    }

    public MenuCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }

    public String getMenuName() {
        return menuName;
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
