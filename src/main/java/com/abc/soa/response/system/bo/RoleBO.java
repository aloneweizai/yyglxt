package com.abc.soa.response.system.bo;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.CommonUtils;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.Role;
import com.abc.soa.response.system.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


/**
 * 
 * 系统角色表
 * 
 **/
@SuppressWarnings("serial")
public class RoleBO {

	private String id;

	private String roleName;

	private String remark;

	private java.util.Date createTime;

	private java.util.Date updateTime;

	private Boolean status;

	private String menuIds;

	private List<Menu> menuList;

	private List<User> adminList;

	public List<User> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<User> adminList) {
		this.adminList = adminList;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getRoleName(){
		return this.roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	//后台没有menuIds值，通过menusList 为menuIds赋值
	public String getMenuIds() {
		if(CommonUtils.checkEmpty(menuIds)){
			StringBuilder sb = new StringBuilder();
			if(!CommonUtils.checkCollectionEmpty(menuList)){
				for (Menu menu: menuList){
					sb.append(menu.getMenuId()).append(",");
				}
			}
			return sb.toString();
		}else{
			return menuIds;
		}
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
}
