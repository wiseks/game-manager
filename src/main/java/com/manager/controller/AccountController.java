package com.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.model.Account;
import com.manager.service.AccountService;
import com.manager.shiro.annotations.ShiroDescription;

/**
 * 账号
 * @author 吴德基  
	created at 2018年7月12日 下午4:05:04
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/list")
	public String getAllAccount(Model model){
		List<Account> list = accountService.getAllAccount();
		model.addAttribute("list", list);
		return "account/list";
	}
	
	@RequestMapping("/createIndex")
	@ShiroDescription(name="创建账号首页")
	public String createAccount(){
		return "account/create";
	}
	
	
	@RequestMapping("/create")
	@ShiroDescription(name="创建账号")
	public String createAccount(Model model,String name,String password){
		boolean value = accountService.createAccount(name, password);
		model.addAttribute("result",value);
		return "account/create";
	}
}
