package com.abc.soa.request.system;

/**
 * Created by Administrator on 2017-05-24.
 */
public class CodeCriteria implements java.io.Serializable {

    private String dictName;

    private Boolean status;

    private Integer page;

    private Integer size;

    private String dictId;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
}
