package com.manager.shiro.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dao.ManagerDao;
import com.manager.model.SysPermission;
import com.manager.model.SysRole;

/**
 * 权限service
 * @author 吴德基  
	created at 2018年7月11日 下午6:36:09
 */
@Service
public class ShiroService {
	
	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	
	
	public void refreshFilterChain(){
		Map<String, String> map = this.filterChainDefinitionMap();
		this.updatePermission(map);
	}
	
	
	private Map<String, String> filterChainDefinitionMap() {
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 拦截器.
		filterChainDefinitionMap.put("/logout", "logout");
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		List<SysRole> list = managerDao.getAllRoleList();
		List<SysPermission> perms = managerDao.getAllSysPermission();
		List<String> roles = new ArrayList<>();
		for (SysRole role : list) {
			roles.add(role.getRole());
		}
		for (SysPermission per : perms) {
			String url = per.getUrl();
			filterChainDefinitionMap.put(url, "perms[" + per.getPermission() + "]");
		}

		// filterChainDefinitionMap.put("/vip/**", "roles[vip]");

		// filterChainDefinitionMap.put("/admin/**", "roles[admin]");

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/**/favicon.ico", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/403", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		return filterChainDefinitionMap;
	}

	/**
	 * 动态更新新的权限
	 * 
	 * @param filterMap
	 */
	private synchronized void updatePermission(Map<String, String> filterMap) {

		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();

			// 获取过滤管理器
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
					.getFilterChainResolver();
			DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver
					.getFilterChainManager();

			// 清空拦截管理器中的存储
			filterManager.getFilterChains().clear();
			/*
			 * 清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去 ps:如果仅仅是更新的话,可以根据这里的 map
			 * 遍历数据修改,重新整理好权限再一起添加
			 */
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

			// 相当于新建的 map, 因为已经清空了
			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
			// 把修改后的 map 放进去
			chains.putAll(filterMap);

			// 这个相当于是全量添加
			for (Map.Entry<String, String> entry : filterMap.entrySet()) {
				// 要拦截的地址
				String url = entry.getKey().trim().replace(" ", "");
				// 地址持有的权限
				String chainDefinition = entry.getValue().trim().replace(" ", "");
				// 生成拦截
				filterManager.createChain(url, chainDefinition);
			}
			// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
			shiroFilterFactoryBean.setLoginUrl("/login");
			// 登录成功后要跳转的链接
			shiroFilterFactoryBean.setSuccessUrl("/index");
			// 未授权界面;
			shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("updatePermission error,filterMap=" +
			// filterMap, e);
		}
	}

}
