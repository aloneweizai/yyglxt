package com.abc.soa.response;

/**
 * Created by stuy on 2017/12/29.
 */
public class KnowledgeTagRel {
    /**
     * 知识库 标签关联Id
     **/
    private String id;

    /**
     * 知识库ID
     **/
    private String knowledgeId;

    /**
     * 关联的标签ID
     **/
    private String tagId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
