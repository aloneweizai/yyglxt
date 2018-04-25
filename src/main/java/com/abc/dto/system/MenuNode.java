package com.abc.dto.system;

import com.abc.soa.response.system.Menu;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/12 18:20
 */
public class MenuNode {

    private Menu menu;

    private List<MenuNode> childNode;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<MenuNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<MenuNode> childNode) {
        this.childNode = childNode;
    }
}
