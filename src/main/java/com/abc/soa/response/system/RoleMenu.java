package com.abc.soa.response.system;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 角色菜单关联表
 * 
 **/
@SuppressWarnings("serial")
public class RoleMenu extends BaseResponse {

	/**ID**/
	private String id;

	/**角色ID**/
	private String roleId;

	/**菜单ID**/
	private String menuId;

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getMenuId(){
		return this.menuId;
	}

	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

}
