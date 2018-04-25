package com.abc.soa.request.system;


import com.abc.soa.request.consumer.BaseRq;

public class MessageSendLogRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phone;
	private String status;
	private String channel;
	private String start;
	private String end;
	private String sendinfo;
	private String sendDate;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}


	public String getSendinfo() {
		return sendinfo;
	}

	public void setSendinfo(String sendinfo) {
		this.sendinfo = sendinfo;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
}
