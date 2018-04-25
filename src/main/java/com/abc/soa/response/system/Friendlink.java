package com.abc.soa.response.system;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by wuhao on 2017-06-06.
 */
public class Friendlink extends BaseResponse {

    private String friendlinkId;//Id
    private String siteId;//siteId
    private String friendlinkctgId;//friendlinkctgId
    private String domain;//网站地址
    private String siteName;//网站名称
    private String logo;//友情链接logo
    private String logoPath;//logo图片地址
    private List<Byte> content;//友情链接logo图片
    private String email;//站长邮箱
    private String description;//网站简介
    private Integer views;//点击次数
    private String isEnabled;//是否显示
    private Integer priority;//排列顺序

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<Byte> getContent() {
        return content;
    }

    public void setContent(List<Byte> content) {
        this.content = content;
    }

    public void setFriendlinkId(String friendlinkId) {
        this.friendlinkId = friendlinkId;
    }

    public String getFriendlinkId() {
        return this.friendlinkId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public void setFriendlinkctgId(String friendlinkctgId) {
        this.friendlinkctgId = friendlinkctgId;
    }

    public String getFriendlinkctgId() {
        return this.friendlinkctgId;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getViews() {
        return this.views;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled() {
        return this.isEnabled;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }
}
