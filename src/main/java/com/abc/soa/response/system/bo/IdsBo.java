package com.abc.soa.response.system.bo;

/**
 * Created by stuy on 2017/6/26.
 * 批量删除和批量审批
 */
public class IdsBo {
    private String []  ids;

    private String text;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
