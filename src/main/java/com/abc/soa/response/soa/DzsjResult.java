package com.abc.soa.response.soa;

/**
 * App登录结果
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-13 4:10 PM
 * @since 1.0.0
 */
public class DzsjResult extends Result {

    private String AccessToken;
    private String expiresTime;

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(String expiresTime) {
        this.expiresTime = expiresTime;
    }

    @Override
    public String toString() {
        return "DzsjResult{" +
                "AccessToken='" + AccessToken + '\'' +
                ", expiresTime='" + expiresTime + '\'' +
                "} " + super.toString();
    }
}
