package com.abc.dto.cms.vote;

import java.util.List;

/**
 * Created by xieyanmao on 2017/7/7.
 */
public class VoteStatisticsDto {

    List<VoteRollTJDto> pclist;

    List<VoteRollTJDto> mobileWeblist;

    List<VoteRollTJDto> tptj;

    List<VoteRollTJDto> tpxxtj;

    private int tpcnt;

    private int llcnt;

    private int llcnts;

    public List<VoteRollTJDto> getPclist() {
        return pclist;
    }

    public void setPclist(List<VoteRollTJDto> pclist) {
        this.pclist = pclist;
    }

    public List<VoteRollTJDto> getMobileWeblist() {
        return mobileWeblist;
    }

    public void setMobileWeblist(List<VoteRollTJDto> mobileWeblist) {
        this.mobileWeblist = mobileWeblist;
    }

    public List<VoteRollTJDto> getTptj() {
        return tptj;
    }

    public void setTptj(List<VoteRollTJDto> tptj) {
        this.tptj = tptj;
    }

    public List<VoteRollTJDto> getTpxxtj() {
        return tpxxtj;
    }

    public void setTpxxtj(List<VoteRollTJDto> tpxxtj) {
        this.tpxxtj = tpxxtj;
    }

    public int getTpcnt() {
        return tpcnt;
    }

    public void setTpcnt(int tpcnt) {
        this.tpcnt = tpcnt;
    }

    public int getLlcnt() {
        return llcnt;
    }

    public void setLlcnt(int llcnt) {
        this.llcnt = llcnt;
    }

    public int getLlcnts() {
        return llcnts;
    }

    public void setLlcnts(int llcnts) {
        this.llcnts = llcnts;
    }
}
