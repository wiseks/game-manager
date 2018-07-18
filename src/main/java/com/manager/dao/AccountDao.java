package com.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.manager.model.Account;

/**
 * account
 * @author 吴德基  
	created at 2018年7月12日 下午4:07:12
 */
public interface AccountDao {

	@Insert("INSERT INTO `account` values(null,#{name},#{password}) ")
	public void insert(Account a);

	@Select("SELECT * FROM `account` WHERE `name` = #{name} limit 1")
	public Account getAccount(String name);
	
	@Select("SELECT * FROM `account`")
	public List<Account> getAllAccount();
	
	@Delete("DELETE FROM `account` WHERE `id` = #{accountId}")
	public void deleteAccount(long accountId);
}
