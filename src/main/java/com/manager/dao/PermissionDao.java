package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.manager.model.SysPermission;

/**
 * 
 * @author 吴德基  
	created at 2018年7月12日 下午4:27:09
 */
public interface PermissionDao {

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
