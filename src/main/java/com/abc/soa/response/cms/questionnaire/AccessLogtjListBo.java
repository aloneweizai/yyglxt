package com.abc.soa.response.cms.questionnaire;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/12 21:13
 */
public class AccessLogtjListBo {

    //浏览统计
    private List<AccessLogRolltjBo> list;

    //pc浏览统计
    private List<AccessLogRolltjBo> pclist;

    //mobileWeb浏览统计
    private List<AccessLogRolltjBo> mobileWeblist;

    //浏览统计总数按时间
    private Integer llcnt;

    //浏览统计总数
    private Integer llcnts;

    public List<AccessLogRolltjBo> getList() {
        return list;
    }

    public void setList(List<AccessLogRolltjBo> list) {
        this.list = list;
    }

    public List<AccessLogRolltjBo> getPclist() {
        return pclist;
    }

    public void setPclist(List<AccessLogRolltjBo> pclist) {
        this.pclist = pclist;
    }

    public List<AccessLogRolltjBo> getMobileWeblist() {
        return mobileWeblist;
    }

    public void setMobileWeblist(List<AccessLogRolltjBo> mobileWeblist) {
        this.mobileWeblist = mobileWeblist;
    }

    public Integer getLlcnt() {
        return llcnt;
    }

    public void setLlcnt(Integer llcnt) {
        this.llcnt = llcnt;
    }

    public Integer getLlcnts() {
        return llcnts;
    }

    public void setLlcnts(Integer llcnts) {
        this.llcnts = llcnts;
    }
}
