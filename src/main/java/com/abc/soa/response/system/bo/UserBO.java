package com.abc.soa.response.system.bo;
import com.abc.common.util.CommonUtils;
import com.abc.soa.response.system.LoginInfo;
import com.abc.soa.response.system.Menu;
import com.abc.soa.response.system.Role;



import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
 * 系统用户表
 *
 **/
public class UserBO extends UserExtendBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**用户名**/
    private String username;

    /**密码**/
    private String password;

    /**昵称**/
    private String nickname;

    /**用户状态**/
    private Boolean status;

    private String roleIds;

    private String phone;

    private List<Role> rolesList;

    private LoginInfo loginInfo;

    private Map<String,List<Menu>> menuMap;
    
    //是否初始密码
    private Boolean isInitPassword;
    

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    //后台不给 只能自己组装
    public String getRoleIds() {
        if(CommonUtils.checkEmpty(roleIds)){
            StringBuilder sb = new StringBuilder();
            if(!CommonUtils.checkCollectionEmpty(rolesList)){
                for (Role role: rolesList){
                    sb.append(role.getId()).append(",");
                }
            }
            return sb.toString();
        }else{
            return roleIds;
        }
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Map<String, List<Menu>> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<String, List<Menu>> menuMap) {
        this.menuMap = menuMap;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

	public Boolean getIsInitPassword() {
		return isInitPassword;
	}

	public void setIsInitPassword(Boolean isInitPassword) {
		this.isInitPassword = isInitPassword;
	}

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
