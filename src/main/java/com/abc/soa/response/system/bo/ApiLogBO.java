package com.abc.soa.response.system.bo;



import java.io.Serializable;
import java.util.Date;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class ApiLogBO extends TableBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 访问接口地址
     **/
    private String uri;
    /**
     * 访问接口地址
     **/
    private String baseuri;

    /**
     * 用户代理
     **/
    private String userAgent;

    /**
     * 接入userId
     **/
    private String userId;

    /**
     * 接入AppId
     **/
    private String appId;

    /**
     * 接入IP地址
     **/
    private String ip;

    /**
     * 访问时间
     **/
    private long inTime;
    /**
     * 访问时间
     **/
    private Date bgtime;

    /**
     * 响应时间
     **/
    private long outTime;

    /**
     * 结果代码
     **/
    private String status;

    /**
     * 附言
     **/
    private String version;

    /**
     * 返回代码
     **/
    private String code;

    /**
     * 返回消息
     **/
    private String message;

    /**
     * 接口方法
     **/
    private String method;


    //开始时间
    private long startTime;
    //结束时间
    private long endTime;

    //每分钟访问次数
    private int minuteCount;
    //每小时访问次数
    private int hourCount;
    //每天访问次数
    private int dayCount;
    
    private String nickname;
    private String appAccessCount;

    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAppAccessCount() {
		return appAccessCount;
	}

	public void setAppAccessCount(String appAccessCount) {
		this.appAccessCount = appAccessCount;
	}

	public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getInTime() {
        return this.inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public long getOutTime() {
        return this.outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getMinuteCount() {
        return minuteCount;
    }

    public void setMinuteCount(int minuteCount) {
        this.minuteCount = minuteCount;
    }

    public int getHourCount() {
        return hourCount;
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

	public String getBaseuri() {
		return baseuri;
	}

	public void setBaseuri(String baseuri) {
		this.baseuri = baseuri;
	}

	public Date getBgtime() {
		return bgtime;
	}

	public void setBgtime(Date bgtime) {
		this.bgtime = bgtime;
	}
}
