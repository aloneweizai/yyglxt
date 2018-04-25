package com.abc.dto.bangbang;

/**
 * @Author liuQi
 * @Date 2017/11/23 14:52
 */
public enum SystemAuditType {

    question("问题"),
    answer("问题回答"),
    comment("问题评论"),
    cheats("秘籍"),
    cheats_comment("秘籍评论");

    SystemAuditType(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public SystemAuditType setDescription(String description) {
        this.description = description;
        return this;
    }
}
