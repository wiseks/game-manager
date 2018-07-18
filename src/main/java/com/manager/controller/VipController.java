package com.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.model.Account;
import com.manager.shiro.annotations.ShiroDescription;

/**
 * 
 * @author 吴德基  
	created at 2018年7月10日 下午8:53:23
 */
@Controller
@RequestMapping("/vip")
public class VipController {

	@RequestMapping("/test")
	@ShiroDescription(name="测试方法")
	public String test(Model model,String msg){
		Account a = new Account();
		a.setName(msg);
		a.setPassword(msg);
		model.addAttribute("name", a.getName());
		return "index/hello";
	}
}
