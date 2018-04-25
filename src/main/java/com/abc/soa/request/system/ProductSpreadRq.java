package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

public class ProductSpreadRq extends BaseRq {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String productname;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
