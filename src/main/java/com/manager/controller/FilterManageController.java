package com.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.shiro.annotations.ShiroDescription;
import com.manager.shiro.services.ShiroService;

@Controller
@RequestMapping("/admin/vip")
public class FilterManageController {
	
	@Autowired
	private ShiroService shiroService;

	@RequestMapping(value = "/change", method = RequestMethod.GET)
    @ResponseBody
    @ShiroDescription(name="重新加载权限拦截列表")
    public String updateFilter() {
		shiroService.refreshFilterChain();

        return "success";
    }
	
	
}
