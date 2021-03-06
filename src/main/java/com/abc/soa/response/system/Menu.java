package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;

/**
 *
 *
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class Menu extends BaseResponse{

    private String menuId;
    private String menuName;
    private String menuUrl;
    private String parentId;
    private String parentName;
    private String perms;
    private String type;
    private String icon;
    private int sort;
    private boolean status;
    private String remark;

    public Menu() {
    }

    public Menu(String menuName, String menuUrl, String parentId, String perms, String type, String icon, int sort, boolean status, String remark) {
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.parentId = parentId;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.sort = sort;
        this.status = status;
        this.remark = remark;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", parentId='" + parentId + '\'' +
                ", perms='" + perms + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
