package com.manager.model;

/**
 * 
 * @author 吴德基  
	created at 2018年7月10日 下午8:54:09
 */
public class SysRolePermission {

	private long permissionId;
	
	private long roleId;

	public long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
}
