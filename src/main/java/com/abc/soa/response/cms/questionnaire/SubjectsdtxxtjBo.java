package com.abc.soa.response.cms.questionnaire;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 题目表
 *
 **/
public class SubjectsdtxxtjBo{

    private String id;
    private String title;
    private Integer cnt;
    private List<AnswerdttjBo> dtlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<AnswerdttjBo> getDtlist() {
        return dtlist;
    }

    public void setDtlist(List<AnswerdttjBo> dtlist) {
        this.dtlist = dtlist;
    }
}
