package com.abc.dto.cms.knowledge;


import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.cms.knowledge.KnowledgeTag;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/8/4 09:12
 */
public class KnowledgeBO extends BaseResponse {
    private boolean success = false;

    private String code;

    private String message;

    private Knowledge data;

    private Knowledge knowledgeBase;

    private List<String> tagIds;        //知识库关联的标签id

    private List<KnowledgeTag> knowTags; //修改传参用

    private List<String> refKnowledgeId;//知识库关联的其他知识库id //新增时用

    private List<Knowledge> refKnowledges;//知识库关联的其他知识库集合 查询时用

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public Knowledge getKnowledgeBase() {
        return knowledgeBase;
    }

    public void setKnowledgeBase(Knowledge knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public List<String> getRefKnowledgeId() {
        return refKnowledgeId;
    }

    public void setRefKnowledgeId(List<String> refKnowledgeId) {
        this.refKnowledgeId = refKnowledgeId;
    }

    public List<Knowledge> getRefKnowledges() {
        return refKnowledges;
    }

    public void setRefKnowledges(List<Knowledge> refKnowledges) {
        this.refKnowledges = refKnowledges;
    }

    public Knowledge getData() {
        return data;
    }

    public void setData(Knowledge data) {
        this.data = data;
    }

    public List<KnowledgeTag> getKnowTags() {
        return knowTags;
    }

    public void setKnowTags(List<KnowledgeTag> knowTags) {
        this.knowTags = knowTags;
    }
}
