package com.abc.soa.request.cms.Event;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/6/29.
 */
public class EventSponsorBo extends BaseResponse {

    private String sponsorId;

    private String sponsorName;

    private String sponsorLxr;

    private String sponsorTel;

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorLxr() {
        return sponsorLxr;
    }

    public void setSponsorLxr(String sponsorLxr) {
        this.sponsorLxr = sponsorLxr;
    }

    public String getSponsorTel() {
        return sponsorTel;
    }

    public void setSponsorTel(String sponsorTel) {
        this.sponsorTel = sponsorTel;
    }

    public String getSponsorEmail() {
        return sponsorEmail;
    }

    public void setSponsorEmail(String sponsorEmail) {
        this.sponsorEmail = sponsorEmail;
    }

    public String getSponsorIntro() {
        return sponsorIntro;
    }

    public void setSponsorIntro(String sponsorIntro) {
        this.sponsorIntro = sponsorIntro;
    }

    private String sponsorEmail;

    private String sponsorIntro;

}
