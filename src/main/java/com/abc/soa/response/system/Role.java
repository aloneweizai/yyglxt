package com.abc.soa.response.system;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 系统角色表
 * 
 **/
@SuppressWarnings("serial")
public class Role extends BaseResponse {

	/**ID**/
	private String id;

	/**角色名称**/
	private String roleName;

	/**备注**/
	private String remark;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;

	/**状态**/
	private Boolean status;

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
}
