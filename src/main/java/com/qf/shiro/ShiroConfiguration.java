package com.qf.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.qf.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShiroConfiguration {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/loginPage");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        Map<String,String> map=new HashMap<String,String>();
        map.put("/loginPage","anon");
        map.put("/login","anon");
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(MyRealm myRealm)
    {
        SecurityManager securityManager = new DefaultWebSecurityManager(myRealm);
        return securityManager;
    }
    @Bean(name = "myRealm")
    public MyRealm getMyRealm()
    {
        return new MyRealm();
    }
    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }
}
