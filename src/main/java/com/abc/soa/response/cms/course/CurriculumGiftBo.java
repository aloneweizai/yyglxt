package com.abc.soa.response.cms.course;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/27 09:46
 */
public class CurriculumGiftBo {

    /**主键**/
    private String id;

    /**关联bb_curriculum_uvip_price的主键**/
    private String giftId;

    /**操作符号：or-或,and-和**/
    private String operSymbol;

    /**操作类型：POINTS-积分，COUPON-优惠券，VIP-会员，AMOUNT-礼包金额**/
    private String operType;

    /**操作值**/
    private String operValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getOperSymbol() {
        return operSymbol;
    }

    public void setOperSymbol(String operSymbol) {
        this.operSymbol = operSymbol;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperValue() {
        return operValue;
    }

    public void setOperValue(String operValue) {
        this.operValue = operValue;
    }
}
