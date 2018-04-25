package com.abc.soa.request.consumer;

import java.io.Serializable;

/**
 * Created by andy on 2017/11/23.
 */
public class AddressListRq implements Serializable{
    private String addressId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
