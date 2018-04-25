package com.abc.soa.request.cms;

import com.abc.soa.request.system.BasePaginationCriteria;

/**
 * Created by zhouzhi on 2017-06-30.
 */
public class PublishSiteCriteria extends BasePaginationCriteria {
    private String templateName;
    private String issueState;  //发布状态

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getIssueState() {
        return issueState;
    }

    public void setIssueState(String issueState) {
        this.issueState = issueState;
    }
}
