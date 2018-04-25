package com.abc.soa.response.system.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class DictBO {
    private String id; //PK
    private String dictId; //字典ID
    private String dictName; //字典名称
    private String fieldKey; //键名
    private String fieldValue; //键值
    private Boolean flag;
    private List<DictBO> nodes = new ArrayList<>();
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        if(sort == null)sort = 0;
        this.sort = sort;
    }


    private Integer sort;//排序



    private Boolean status; //状态
    private String createUser; //创建用户
    private Date createTime; //创建时间
    private Date lastUser; //修改用户
    private Date lastUpdate; //上次修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUser() {
        return lastUser;
    }

    public void setLastUser(Date lastUser) {
        this.lastUser = lastUser;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<DictBO> getNodes() {
        return nodes;
    }

    public void setNodes(List<DictBO> nodes) {
        this.nodes = nodes;
    }
}