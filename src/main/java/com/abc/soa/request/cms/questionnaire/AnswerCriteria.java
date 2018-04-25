package com.abc.soa.request.cms.questionnaire;

import com.abc.common.util.PagerSpec;

import java.util.Date;

/**
 * @Author liuqi
 * @Date 2017/7/12 14:23
 */
public class AnswerCriteria {

    private String startDate;

    private String endDate;

    private Integer page;

    private Integer size;

    public AnswerCriteria withStartDate(String startDate){
        this.startDate = startDate;
        return this;
    }

    public AnswerCriteria withEndDate(String endDate){
        this.endDate = endDate;
        return this;
    }

    public AnswerCriteria withPagerSpec(PagerSpec pagerSpec){
        this.page = pagerSpec.getCurrentPage();
        this.size = pagerSpec.getPerPageNum();
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
