package com.abc.soa.response.cszj.bo;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公众号信息
 *
 * @author zhushuai 2017-8-1
 */
public class GzhInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    @NotEmpty
    private String gzhName;
    @NotEmpty
    private String appid;
    @NotEmpty
    private String secret;
    @NotEmpty
    private String tokenStr;
    @NotEmpty
    private String serverUrl;
    
    private String userToken;//usertoken
    private Date userTokenUpdate;//token更新时间
    
    private String jsapi_ticket;
    private Date jsapiTicketUpdate;
    
    private Date creatDate;
    private Date lastupdate;

    
    
    public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	public Date getJsapiTicketUpdate() {
		return jsapiTicketUpdate;
	}

	public void setJsapiTicketUpdate(Date jsapiTicketUpdate) {
		this.jsapiTicketUpdate = jsapiTicketUpdate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzhName() {
        return gzhName;
    }

    public void setGzhName(String gzhName) {
        this.gzhName = gzhName;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Date getUserTokenUpdate() {
		return userTokenUpdate;
	}

	public void setUserTokenUpdate(Date userTokenUpdate) {
		this.userTokenUpdate = userTokenUpdate;
	}


}
