package com.abc.soa.response.cszj.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by relic5 on 2017/6/19.
 */
public class TemplateBO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String template_id;//模板ID
    private String title;//模板标题
    private String primary_industry;    //模板所属行业的一级行业
    private String deputy_industry;  //模板所属行业的二级行业
    private String content;//模板内容
    private String example;//示例
    private String contentstr;//模板内容
    private String examplestr;//示例

    private Date lastupdate; //同步时间

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getContentstr() {
        return contentstr;
    }

    public void setContentstr(String contentstr) {
        this.contentstr = contentstr;
    }

    public String getExamplestr() {
        return examplestr;
    }

    public void setExamplestr(String examplestr) {
        this.examplestr = examplestr;
    }
}
