package com.abc.dto.cms.vote;

import java.util.Date;
import java.util.List;

/**
 * Created by relic5 on 2017/6/27.
 */
public class VoteSubjectDto {

    private String id;

    private String voteId;

    private String subject;

    private String form;

    private Boolean required = false;

    private int sort;

    private String createTime;

    private String lastUpdate;

    private List<VoteSubjectItemDto> itemList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<VoteSubjectItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<VoteSubjectItemDto> itemList) {
        this.itemList = itemList;
    }
}
