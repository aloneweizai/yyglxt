package com.abc.application;

import java.util.*;

import com.abc.application.redis.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @Author liuqi
 * @Date 2017/6/14 16:54
 */
@Configuration
public class ShiroConfig extends SpringCtxHolder {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	@Bean
	public UserRealm userRealm() {
		return new UserRealm();
	}

	/**
	 * 配置shiro redisManager
	 * 
	 * @return
	 */
	@Bean
	public RedisManager redisManager() {
		String[] hostPorts = getProperty("abc.redis.hostName").split(";");
		RedisManager redisManager;
		if (hostPorts.length == 1) {
			logger.info("单节点redis加载....");
			redisManager = new SingleRedisManager();
			redisManager.setHost(hostPorts[0].split(":")[0]);
			redisManager.setPort(Integer.valueOf(hostPorts[0].split(":")[1]));
			redisManager.setExpire(1800);// 配置缓存过期时间
			redisManager.setTimeout(0);
		} else {
			logger.info("多节点redis加载，节点个数:" + hostPorts.length);
			redisManager = new ShareRedisManager();
		}
		return redisManager;
	}

	/**
	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
	 * 
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * Session Manager 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(userRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		// 注入记住我管理器;TODO
		// securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。 注意：单独一个ShiroFilterFactoryBean配置是或报错的
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 临时新增
		filterChainDefinitionMap.put("/cms/pub/hotContentList", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/cms/pub/hotLabel", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/cms/pub/hotQuestion", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/cms/pub/hotDiscussion", DefaultFilter.anon.name());

		filterChainDefinitionMap.put("/login.php", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/logon", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/static/**", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/css/**", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/images/**", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/js/**", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/fonts/**", DefaultFilter.anon.name());
		filterChainDefinitionMap.put("/login/out.php", DefaultFilter.logout.name());
		filterChainDefinitionMap.put("/**", DefaultFilter.authc.name());

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor initLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn({ "lifecycleBeanPostProcessor" })
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
