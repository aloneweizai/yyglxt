package com.abc.soa.request.cszj;

import com.abc.soa.request.consumer.BaseRq;

public class WxTemplateRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;//模板标题
    private String primary_industry;    //模板所属行业的一级行业
    private String deputy_industry;  //模板所属行业的二级行业

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimary_industry() {
        return primary_industry;
    }

    public void setPrimary_industry(String primary_industry) {
        this.primary_industry = primary_industry;
    }

    public String getDeputy_industry() {
        return deputy_industry;
    }

    public void setDeputy_industry(String deputy_industry) {
        this.deputy_industry = deputy_industry;
    }
}
