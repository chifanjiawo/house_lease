package com.house.demo.config;


import com.house.demo.shiro.AccountRealm;
import com.house.demo.shiro.JwtFilter;
import com.house.demo.shiro.cache.RedisCacheManage;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import javax.servlet.Filter;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author xjj
 */
@Configuration
@Slf4j
public class ShiroConfig {


    /**
     * 注入 securityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm());
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    @Bean("lifecycleBeanPostProcessor")
    //管理shiro生命周期
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
    @Bean
    public AccountRealm accountRealm() {

        AccountRealm accountRealm = new AccountRealm();
        accountRealm.setCacheManager(new RedisCacheManage());
        accountRealm.setCachingEnabled(true);
        accountRealm.setAuthenticationCachingEnabled(true);
        accountRealm.setAuthorizationCachingEnabled(true);
        accountRealm.setAuthenticationCacheName("authenticationCache");
        accountRealm.setAuthorizationCacheName("authorizationCache");
        return accountRealm;
    }

    @Bean
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt",new JwtFilter());
        factoryBean.setFilters(filters);
        factoryBean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/user/login","anon");
//        filterMap.put("/user/register","anon");
//
//        filterMap.put("/index/**","anon");
//        filterMap.put("/**","jwt");

        factoryBean.setFilterChainDefinitionMap(filterMap);

        return factoryBean;
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        return new ShiroFilterChainDefinition() {
            @Override
            public Map<String, String> getFilterChainMap() {
                return null;
            }
        };
    }

    //Shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }




}
