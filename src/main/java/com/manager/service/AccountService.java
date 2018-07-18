package com.manager.service;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.dao.AccountDao;
import com.manager.dao.AccountRoleDao;
import com.manager.model.Account;

/**
 * account
 * @author 吴德基  
	created at 2018年7月12日 下午4:06:38
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	public boolean createAccount(String name,String password){
		Account account = accountDao.getAccount(name);
		if(account!=null){
			return false;
		}
		Account a = new Account();
		a.setName(name);
		Md5Hash mh = new Md5Hash(name, password, 1);
		a.setPassword(mh.toString());
		accountDao.insert(a);
		return true;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean deleteAccount(String name){
		Account account = accountDao.getAccount(name);
		if(account==null){
			return false;
		}
		accountDao.deleteAccount(account.getId());
		accountRoleDao.deleteSysAccountRole(account.getId());
		return true;
	}
	
	public List<Account> getAllAccount(){
		return accountDao.getAllAccount();
	}
}
