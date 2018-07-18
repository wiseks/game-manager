package com.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manager.model.Account;
import com.manager.service.AccountService;
import com.manager.shiro.annotations.ShiroDescription;

/**
 * 账号
 * @author 吴德基  
	created at 2018年7月12日 下午4:05:04
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getAllAccount(Model model){
		List<Account> list = accountService.getAllAccount();
		model.addAttribute("list", list);
		return "account/list";
	}
	
	@RequestMapping(value="/createIndex",method=RequestMethod.GET)
	@ShiroDescription(name="创建账号首页")
	public String createAccount(){
		return "account/create";
	}
	
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ShiroDescription(name="创建账号")
	public String createAccount(Model model,String name,String password){
		boolean value = accountService.createAccount(name, password);
		model.addAttribute("result",value);
		return "account/create";
	}
}
