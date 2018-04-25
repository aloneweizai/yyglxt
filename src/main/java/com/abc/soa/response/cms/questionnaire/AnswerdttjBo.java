package com.abc.soa.response.cms.questionnaire;

import java.io.Serializable;

/**
 *
 * CMS活动表
 *
 **/
public class AnswerdttjBo  {

    /**统计项**varchar(64)**/
    private String content;

    /**值**int(11)**/
    private Integer cnt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
