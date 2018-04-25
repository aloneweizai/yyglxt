package com.abc.soa.request.financed;

import com.abc.soa.request.consumer.BaseRq;

public class InvoicekjlogRq extends BaseRq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fpqqlsh; //请求流水号
	private String fp_hm; //发票号码

	public String getFpqqlsh() {
		return fpqqlsh;
	}

	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}

	public String getFp_hm() {
		return fp_hm;
	}

	public void setFp_hm(String fp_hm) {
		this.fp_hm = fp_hm;
	}
}
