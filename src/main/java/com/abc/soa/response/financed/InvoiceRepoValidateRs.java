package com.abc.soa.response.financed;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.financed.bo.InvoiceRepo;

import java.util.List;

public class InvoiceRepoValidateRs extends BaseResponse{

	private static final long serialVersionUID = 1L;

    private Boolean data;


	public Boolean getData() {
		return data;
	}

	public void setData(Boolean data) {
		this.data = data;
	}
}
