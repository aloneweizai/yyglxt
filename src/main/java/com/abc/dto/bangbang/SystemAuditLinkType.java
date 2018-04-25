package com.abc.dto.bangbang;

/**
 * @Author liuQi
 * @Date 2017/11/23 14:52
 */
public enum SystemAuditLinkType {

    question("/topic/xiangqing/"),
    answer("/topic/xiangqing/"),
    comment("/topic/xiangqing/"),
    cheats("/cheats/details/"),
    cheats_comment("/cheats/details/");

    SystemAuditLinkType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public SystemAuditLinkType setDescription(String description) {
        this.description = description;
        return this;
    }
}
