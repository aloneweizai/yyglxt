package com.abc.soa.response.financed.bo;

import java.util.Date;
import java.util.List;


public class InvoicekjlogBO {
	private String returnCode;
	private String returnMessage;
	private String KPLX;//开票类型
	private String FPQQLSH;//发票请求流水号
	private String FP_DM;//发票代码
	private String FP_HM;//发票号码
	private String JYM;//发票校验码
	private String KPRQ;//开票日期
	private String PDF_URL;//pdf下载地址
	private String SP_URL;//收票地址
	private String TBSTATUS="0";//同步状态 0：未同步
	private String sendStr;//请求内容

	public String getFP_DM() {
		return FP_DM;
	}

	public void setFP_DM(String FP_DM) {
		this.FP_DM = FP_DM;
	}

	public String getFP_HM() {
		return FP_HM;
	}

	public void setFP_HM(String FP_HM) {
		this.FP_HM = FP_HM;
	}

	public String getFPQQLSH() {
		return FPQQLSH;
	}

	public void setFPQQLSH(String FPQQLSH) {
		this.FPQQLSH = FPQQLSH;
	}

	public String getJYM() {
		return JYM;
	}

	public void setJYM(String JYM) {
		this.JYM = JYM;
	}

	public String getKPLX() {
		return KPLX;
	}

	public void setKPLX(String KPLX) {
		this.KPLX = KPLX;
	}

	public String getKPRQ() {
		return KPRQ;
	}

	public void setKPRQ(String KPRQ) {
		this.KPRQ = KPRQ;
	}

	public String getPDF_URL() {
		return PDF_URL;
	}

	public void setPDF_URL(String PDF_URL) {
		this.PDF_URL = PDF_URL;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getSendStr() {
		return sendStr;
	}

	public void setSendStr(String sendStr) {
		this.sendStr = sendStr;
	}

	public String getSP_URL() {
		return SP_URL;
	}

	public void setSP_URL(String SP_URL) {
		this.SP_URL = SP_URL;
	}

	public String getTBSTATUS() {
		return TBSTATUS;
	}

	public void setTBSTATUS(String TBSTATUS) {
		this.TBSTATUS = TBSTATUS;
	}
}
