package com.manager.shiro.mapping;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.dao.ManagerDao;
import com.manager.model.SysPermission;
import com.manager.shiro.annotations.ShiroDescription;
import com.manager.shiro.services.ShiroService;

/**
 * urlmapping
 * @author 吴德基  
	created at 2018年7月11日 下午6:35:58
 */
@Service
public class ShiroUrlMapping {

	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private ShiroService shiroService;

	@Autowired
	private ApplicationContext applicationContext;

	

	@PostConstruct
	private void initSysPermission() {
		List<SysPermission> perList = managerDao.getAllSysPermission();
		Set<String> urlSet = new HashSet<>();
		for (SysPermission per : perList) {
			urlSet.add(per.getUrl());
		}
		Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Controller.class);
		for (Object obj : beanMap.values()) {
			RequestMapping classMapping = obj.getClass().getAnnotation(RequestMapping.class);
			String className = obj.getClass().getSimpleName();
			String base = "";
			if (classMapping != null) {
				String[] values = classMapping.value();
				if (values.length > 0) {
					base = values[0];
				}
			}
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
				if (methodRequestMapping != null) {
					String[] methodValues = methodRequestMapping.value();
					if (methodValues.length > 0) {
						String methodName = method.getName();
						String name = className + ":" + methodName;
						String url = base + methodValues[0];
						if (!urlSet.contains(url)) {
							ShiroDescription shiroDescription = method.getAnnotation(ShiroDescription.class);
							if (shiroDescription != null) {
								System.out.println(shiroDescription.name() + ":" + url + ",permission=" + name);
								SysPermission per = new SysPermission();
								per.setAvailable(false);
								per.setName(shiroDescription.name());
								per.setParentId(1L);
								per.setParentIds("1");
								per.setPermission(name);
								per.setResourceType("");
								per.setUrl(url);
								managerDao.insertSysPermission(per);
							}
						}
					}
				}
			}
		}
		shiroService.refreshFilterChain();
	}

	
}
