package com.abc.soa.response.cms.knowledge;

/**
 * @Author liuqi
 * @Date 2017/9/8 09:35
 */
public class KnowledgeTag {

    /**
     * 标签ID
     **/
    private String id;

    /**
     * 标签名称
     **/
    private String name;

    /**
     * 标签描述
     **/
    private String description;

    /**
     * 有效状态
     **/
    private Boolean status;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 创建人
     **/
    private String createUser;

    /**
     * 修改人
     **/
    private String updateUser;

    /**
     * 标签类型
     */
    private String tagType;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getTagType() {
        return tagType;
    }

    public KnowledgeTag(){}

    public KnowledgeTag setTagType(String tagType) {
        this.tagType = tagType;
        return this;
    }

    public KnowledgeTag(String name, String description, Boolean status, String tagType) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.tagType = tagType;
    }
}
