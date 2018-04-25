package com.abc.soa.response.financed.bo;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;

/**
 * Created by pan on 2017-08-15.
 */
public class InvoiceRepoId extends BaseResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private InvoiceRepoBo data;

    public InvoiceRepoBo getData() {
        return data;
    }

    public void setData(InvoiceRepoBo data) {
        this.data = data;
    }
}
