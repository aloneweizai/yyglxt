package com.abc.soa.response.system.bo;

import com.abc.common.soa.response.BaseResponse;

/**
 *
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class DictUpdateBO extends BaseResponse {
    private String id;
    private String dictId;
    private String dictName;
    private String fieldKey;
    private String fieldValue;
    private boolean status;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "DictUpdateBO{"+
                "id="+id+
                "dictId="+dictId+
                "dictName="+dictName+
                "fieldKey="+fieldKey+
                "fieldValue+"+fieldValue+
                "status="+status+"}";
    }
}

