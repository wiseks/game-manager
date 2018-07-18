package com.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.manager.dao.ManagerDao;
import com.manager.model.Account;
import com.manager.shiro.annotations.ShiroDescription;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author 吴德基  
	created at 2018年7月10日 下午8:53:19
 */
@Controller
public class ManagerController {
	
	@Autowired
	private ManagerDao managerDao;

	private static final Log log = LogFactory.getLog(ManagerController.class);
	
	@RequestMapping("/test")
	@ApiOperation(value = "测试")
	@ShiroDescription(name="测试方法")
	public String test(Model model,String msg){
		
		Account account = managerDao.getAccount(msg);
		if(account!=null){
			model.addAttribute("name", "账号已存在");
			return "index/hello";
		}
		Account a = new Account();
		a.setName(msg);
		
		Md5Hash mh = new Md5Hash(msg, msg, 1);
		a.setPassword(mh.toString());
		managerDao.insert(a);
		log.info("msg:"+msg);
		model.addAttribute("name", a.getName());
		return "index/hello";
	}
       
}
