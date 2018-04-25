package com.abc.soa.response.system;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 系统用户角色关联�?
 * 
 **/
@SuppressWarnings("serial")
public class UserRole{

	/**ID**/
	private String id;

	/**用户ID**/
	private String userId;

	/**角色ID**/
	private String roleId;

	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getRoleId(){
		return this.roleId;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

}
