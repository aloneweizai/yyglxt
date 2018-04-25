package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

public class MsgautoRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String keyString;//关键字
    private String id;
    private String setting;
    private String searchTp;

    public String getSearchTp() {
        return searchTp;
    }

    public void setSearchTp(String searchTp) {
        this.searchTp = searchTp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }
}
