package com.abc.soa.response.cms.knowledge;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/7 14:30
 */
public class KnowledgeBO {

    private Knowledge knowledgeBase;

    private List<String> tagIds;        //知识库关联的标签id

    private List<String> refKnowledgeId;//知识库关联的其他知识库id

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public Knowledge getKnowledgeBase() {
        return knowledgeBase;
    }

    public KnowledgeBO setKnowledgeBase(Knowledge knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
        return this;
    }

    public List<String> getRefKnowledgeId() {
        return refKnowledgeId;
    }

    public void setRefKnowledgeId(List<String> refKnowledgeId) {
        this.refKnowledgeId = refKnowledgeId;
    }
}
