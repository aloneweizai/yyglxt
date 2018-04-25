package com.abc.soa.request.cms;

import com.abc.soa.request.system.BasePaginationCriteria;

/**
 * Created by stuy on 2017/7/10.
 */
public class CodePaginationCriteria extends BasePaginationCriteria {

    private String dictName;

    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }


}
