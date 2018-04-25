package com.abc.soa.response.soa;

import com.abc.common.soa.response.BaseResponse;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-13 9:08 AM
 * @since 1.0.0
 */
public class PublicKey extends BaseResponse {

    public byte[] pk;

    public byte[] getPk() {
        return pk;
    }

    public void setPk(byte[] pk) {
        this.pk = pk;
    }
}
