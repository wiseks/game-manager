package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.manager.model.SysRole;

/**
 * 
 * @author 吴德基  
	created at 2018年7月12日 下午4:25:20
 */
public interface RoleDao {

	@Select("<script>" + "SELECT * FROM `sys_role` WHERE `id` IN "
			+ "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" + "#{item}"
			+ "</foreach>" + "</script>")
	public List<SysRole> getSysRoleList(@Param("ids") List<Long> ids);

	@Select("SELECT * FROM `sys_role` ")
	public List<SysRole> getAllRoleList();
	
	@Delete("DELETE FROM `sys_role` WHERE `id` = #{roleId}")
	public void deleteRole(long roleId);
}
