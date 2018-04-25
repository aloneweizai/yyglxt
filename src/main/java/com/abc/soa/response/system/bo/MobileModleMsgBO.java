package com.abc.soa.response.system.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 18:19
 */
public class MobileModleMsgBO {
    /**
     * 主键
     */
    private String id;
    /**
     * 模板标题
     */
    private String title;
    /**
     * 模板签名
     */
    private String sign;
    /**
     * 短信类型(营销、行业)(industry, marketing)
     */
    private String type;
    /**
     * 短信模板内容
     */
    private String content;
    /**
     * 短信模板内容
     */
    private String contentstr;
    /**
     * 模板状态（审核中、通过、失败)(review, success, defeat)
     */
    private String status;
    /**
     * 原因(一般模板审核没通过会有原因)
     */
    private String reason;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 同步时间
     */
    private Date synchronizeTime;
    /**
     * 模板ID
     */
    private String templateId;
    /**
     * 创建者ID
     */
    private String ownerId;
    /**
     * 创建者昵称
     */
    private String ownerName;
    /**
     * 已发送行业短信条数
     */
    private String ownerIndustry;
    /**
     * 已发送营销短信条数
     */
    private String ownerMarketing;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getSynchronizeTime() {
        return synchronizeTime;
    }

    public void setSynchronizeTime(Date synchronizeTime) {
        this.synchronizeTime = synchronizeTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerIndustry() {
        return ownerIndustry;
    }

    public void setOwnerIndustry(String ownerIndustry) {
        this.ownerIndustry = ownerIndustry;
    }

    public String getOwnerMarketing() {
        return ownerMarketing;
    }

    public void setOwnerMarketing(String ownerMarketing) {
        this.ownerMarketing = ownerMarketing;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentstr() {
        return contentstr;
    }

    public void setContentstr(String contentstr) {
        this.contentstr = contentstr;
    }
}
