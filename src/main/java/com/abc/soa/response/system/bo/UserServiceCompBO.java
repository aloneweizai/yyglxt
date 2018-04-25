package com.abc.soa.response.system.bo;


import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class UserServiceCompBO extends TableBO implements Serializable {

    /**
     *1-5户
     */
    private String oneToFive;

    /**
     * 6-10户
     */
    private String sixToTen;

    /**
     * 11-20户
     */
    private String elevenToTwenty;
    /**
     * 21-30户
     */
    private String twentyOneToThirty;
    /**
     * 31-40户
     */
    private String thirtyOneToForty;
    /**
     * 41-50户
     */
    private String fortyOneToFifty;
    /**
     * 51-100户
     */
    private String fiftyOneToHundred;
    /**
     * 101-150户
     */
    private String hundredOneToHundredFifty;
    /**
     * 151-200户
     */
    private String hundredFiftyOneToTwoHundred;
    /**
     * 200户以上
     */
    private String twoHundredAbove;

    public String getElevenToTwenty() {
        return elevenToTwenty;
    }

    public void setElevenToTwenty(String elevenToTwenty) {
        this.elevenToTwenty = elevenToTwenty;
    }

    public String getFiftyOneToHundred() {
        return fiftyOneToHundred;
    }

    public void setFiftyOneToHundred(String fiftyOneToHundred) {
        this.fiftyOneToHundred = fiftyOneToHundred;
    }

    public String getFortyOneToFifty() {
        return fortyOneToFifty;
    }

    public void setFortyOneToFifty(String fortyOneToFifty) {
        this.fortyOneToFifty = fortyOneToFifty;
    }

    public String getHundredFiftyOneToTwoHundred() {
        return hundredFiftyOneToTwoHundred;
    }

    public void setHundredFiftyOneToTwoHundred(String hundredFiftyOneToTwoHundred) {
        this.hundredFiftyOneToTwoHundred = hundredFiftyOneToTwoHundred;
    }

    public String getHundredOneToHundredFifty() {
        return hundredOneToHundredFifty;
    }

    public void setHundredOneToHundredFifty(String hundredOneToHundredFifty) {
        this.hundredOneToHundredFifty = hundredOneToHundredFifty;
    }

    public String getOneToFive() {
        return oneToFive;
    }

    public void setOneToFive(String oneToFive) {
        this.oneToFive = oneToFive;
    }

    public String getSixToTen() {
        return sixToTen;
    }

    public void setSixToTen(String sixToTen) {
        this.sixToTen = sixToTen;
    }

    public String getThirtyOneToForty() {
        return thirtyOneToForty;
    }

    public void setThirtyOneToForty(String thirtyOneToForty) {
        this.thirtyOneToForty = thirtyOneToForty;
    }

    public String getTwentyOneToThirty() {
        return twentyOneToThirty;
    }

    public void setTwentyOneToThirty(String twentyOneToThirty) {
        this.twentyOneToThirty = twentyOneToThirty;
    }

    public String getTwoHundredAbove() {
        return twoHundredAbove;
    }

    public void setTwoHundredAbove(String twoHundredAbove) {
        this.twoHundredAbove = twoHundredAbove;
    }
}
