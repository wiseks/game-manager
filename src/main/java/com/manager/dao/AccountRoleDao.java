package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.manager.model.SysAccountRole;

/**
 * 
 * @author 吴德基  
	created at 2018年7月12日 下午4:28:33
 */
public interface AccountRoleDao {

	@Select("SELECT * FROM `sys_account_role` WHERE `uid` = #{accountId}")
	@Results({ @Result(property = "roleId", column = "role_id"), })
	public List<SysAccountRole> getSysAccountRole(long accountId);
	
	@Insert("INSERT INTO `sys_account_role` (`uid`,`role_id`) VALUES (#{uid},#{roleId})")
	public void insertSysAccountRole(SysAccountRole role);
	
	@Delete("DELETE FROM `sys_account_role` WHERE `uid` = #{accountId} ")
	public void deleteSysAccountRole(long accountId);
	
	@Delete("DELETE FROM `sys_account_role` WHERE `role_id` = #{roldId} ")
	public void deleteSysAccountRoleByRoleId(long roldId);
}
