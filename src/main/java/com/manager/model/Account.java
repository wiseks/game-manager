package com.manager.model;

import java.util.List;

/**
 * 
 * @author 吴德基  
	created at 2018年7月10日 下午8:53:49
 */
public class Account {

	private long id;
	
	private String name;
	
	private String password;
	
	private List<SysRole> roleList;// 一个用户具有多个角色  
    
    public List<SysRole> getRoleList() {  
       return roleList;  
    }  
   
    public void setRoleList(List<SysRole> roleList) {  
       this.roleList = roleList;  
    }  

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
