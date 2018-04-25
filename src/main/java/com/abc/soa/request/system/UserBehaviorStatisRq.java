package com.abc.soa.request.system;

import com.abc.soa.request.consumer.BaseRq;

import java.util.List;

/**
 * API 查询条件
 * @author zhushuai 2017-10-18
 *
 */
public class UserBehaviorStatisRq extends BaseRq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String menu;
    private String menuList;
    private String menua;
	private String menub;
	private String menuc;
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuList() {
		return menuList;
	}

	public void setMenuList(String menuList) {
		this.menuList = menuList;
	}

	public String getMenua() {
		return menua;
	}

	public void setMenua(String menua) {
		this.menua = menua;
	}

	public String getMenub() {
		return menub;
	}

	public void setMenub(String menub) {
		this.menub = menub;
	}

	public String getMenuc() {
		return menuc;
	}

	public void setMenuc(String menuc) {
		this.menuc = menuc;
	}
}
