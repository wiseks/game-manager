package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.manager.model.SysRolePermission;

/**
 * 
 * @author 吴德基  
	created at 2018年7月12日 下午4:28:58
 */
public interface RolePermissionDao {

	@Select("SELECT * FROM `sys_role_permission` WHERE `role_id` = #{roldId}")
	@Results({ @Result(property = "permissionId", column = "permission_id"),
			@Result(property = "roleId", column = "role_id") })
	public List<SysRolePermission> getSysRolePermission(long roldId);
	
	@Insert("INSERT INTO `sys_role_permission` (`role_id`,`permission_id`) VALUES (#{roleId},#{permissionId}) ")
	public void insertSysRolePermission(SysRolePermission entity);
	
	@Delete("DELETE FROM `sys_role_permission` WHERE `role_id` = #{roleId}")
	public void deleteSysRolePermission(int roleId);
}
