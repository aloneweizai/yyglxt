package com.abc.soa.response.system.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 18:19
 */
public class WxModleMsglogBO {
    private String id;//日志ID
    private String returncode; //发送结果吗
    private String returnmsg;//发送结果
    private String templateval;//模板消息
    private String username; //用户名
    private String nickname; //昵称
    private Date createdate; //发送时间
    private String templatevalstr;//模板消息

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    public String getTemplateval() {
        return templateval;
    }

    public void setTemplateval(String templateval) {
        this.templateval = templateval;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTemplatevalstr() {
        return templatevalstr;
    }

    public void setTemplatevalstr(String templatevalstr) {
        this.templatevalstr = templatevalstr;
    }
}
