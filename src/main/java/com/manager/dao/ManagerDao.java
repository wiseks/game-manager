package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.manager.model.Account;
import com.manager.model.SysAccountRole;
import com.manager.model.SysPermission;
import com.manager.model.SysRole;
import com.manager.model.SysRolePermission;

/**
 * 
 * @author 吴德基 created at 2018年7月10日 下午8:53:44
 */
public interface ManagerDao {

	@Insert("INSERT INTO `account` values(null,#{name},#{password}) ")
	public void insert(Account a);

	@Select("SELECT * FROM `account` WHERE `name` = #{name} limit 1")
	public Account getAccount(String name);

	@Select("SELECT * FROM `sys_account_role` WHERE `uid` = #{uid}")
	@Results({ @Result(property = "roleId", column = "role_id"), })
	public List<SysAccountRole> getSysAccountRole(long uid);

	@Select("<script>" + "SELECT * FROM `sys_role` WHERE `id` IN "
			+ "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" + "#{item}"
			+ "</foreach>" + "</script>")
	public List<SysRole> getSysRoleList(@Param("ids") List<Long> ids);

	@Select("SELECT * FROM `sys_role` ")
	public List<SysRole> getAllRoleList();

	@Select("SELECT * FROM `sys_role_permission` WHERE `role_id` = #{roldId}")
	@Results({ @Result(property = "permissionId", column = "permission_id"),
			@Result(property = "roleId", column = "role_id") })
	public List<SysRolePermission> getSysRolePermission(long roldId);

	@Select("<script>" + "SELECT * FROM `sys_permission` WHERE `id` IN "
			+ "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" + "#{item}"
			+ "</foreach>" + "</script>")
	@Results({ @Result(property = "resourceType", column = "resource_type"),
			@Result(property = "parentId", column = "parent_id"),
			@Result(property = "parentIds", column = "parent_ids") })
	public List<SysPermission> getSysPermissionList(@Param("ids") List<Long> ids);

	@Select("SELECT * FROM `sys_permission`")
	@Results({ @Result(property = "resourceType", column = "resource_type"),
			@Result(property = "parentId", column = "parent_id"),
			@Result(property = "parentIds", column = "parent_ids") })
	public List<SysPermission> getAllSysPermission();
	
	@Insert("INSERT INTO `sys_permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`url`,`permission`,`resource_type`) values (null,#{available},#{name},#{parentId},#{parentIds},#{url},#{permission},#{resourceType})")
	public void insertSysPermission(SysPermission per);
}
