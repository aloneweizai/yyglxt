package com.abc.dto.cms.vote;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by relic5 on 2017/6/19.
 */
public class VoteDto {

    private String id;

    private String name;

    private String startTime;

    private String endTime;

    private Boolean login = false;

    private String channel;

    private Boolean quickVote = false;

    private String privacyVote;

    private String privacyPassword;

    private Boolean dayVote = false;

    private Boolean showResult = false;

    private String startIntro;

    private String endIntro;

    private Boolean access = false;

    private String header;

    private String footer;

    private String url = "";

    private int status;//

    private String createTime;

    private String lastUpdate;

    private Boolean validateCode = false;

    private Boolean hiddenResult = false;

    private int nop;

    private int nov;

    //各投票选项
    private List<VoteSubjectDto> subjectList;

    //各项要采集的用户数据
    private List<VoteCollectionDto> additionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getQuickVote() {
        return quickVote;
    }

    public void setQuickVote(Boolean quickVote) {
        this.quickVote = quickVote;
    }

    public String getPrivacyVote() {
        return privacyVote;
    }

    public void setPrivacyVote(String privacyVote) {
        this.privacyVote = privacyVote;
    }

    public String getPrivacyPassword() {
        return privacyPassword;
    }

    public void setPrivacyPassword(String privacyPassword) {
        this.privacyPassword = privacyPassword;
    }

    public Boolean getDayVote() {
        return dayVote;
    }

    public void setDayVote(Boolean dayVote) {
        this.dayVote = dayVote;
    }

    public Boolean getShowResult() {
        return showResult;
    }

    public void setShowResult(Boolean showResult) {
        this.showResult = showResult;
    }

    public String getStartIntro() {
        return startIntro;
    }

    public void setStartIntro(String startIntro) {
        this.startIntro = startIntro;
    }

    public String getEndIntro() {
        return endIntro;
    }

    public void setEndIntro(String endIntro) {
        this.endIntro = endIntro;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Boolean getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(Boolean validateCode) {
        this.validateCode = validateCode;
    }

    public Boolean getHiddenResult() {
        return hiddenResult;
    }

    public void setHiddenResult(Boolean hiddenResult) {
        this.hiddenResult = hiddenResult;
    }

    public int getNop() {
        return nop;
    }

    public void setNop(int nop) {
        this.nop = nop;
    }

    public int getNov() {
        return nov;
    }

    public void setNov(int nov) {
        this.nov = nov;
    }

    public List<VoteSubjectDto> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<VoteSubjectDto> subjectList) {
        this.subjectList = subjectList;
    }

    public List<VoteCollectionDto> getAdditionList() {
        return additionList;
    }

    public void setAdditionList(List<VoteCollectionDto> additionList) {
        this.additionList = additionList;
    }
}
