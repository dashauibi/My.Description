package cn.edu.svtcc.demo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 注入自定义的realm
     *
     * @return MyRealm
     */

    private static final Logger logger =
            LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public MyRealm myAuthRealm() {
        MyRealm myRealm = new MyRealm();
        logger.info("====myRealm注册完成=====");
        return myRealm;
    }

    /**
     * 配置
     *
     * @return MyRealm
     */
    @Bean
    public SecurityManager securityManager() {
// 将自定义realm加进来
        DefaultWebSecurityManager securityManager = new
                DefaultWebSecurityManager(myAuthRealm());
        logger.info("====securityManager注册完成====");
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
// 定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new
                ShiroFilterFactoryBean();
// 设置自定义的securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
// 设置默认登录的url，身份认证失败会访问该url
        shiroFilterFactoryBean.setLoginUrl("/admin/springboot");
// 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/admin/main");
// 设置未授权界面，权限认证失败会访问该url
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/springboot");
// LinkedHashMap是有序的，进行顺序拦截器配置
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        // “/user/admin” 开头的需要身份认证，authc表示要身份认证
//        filterChainMap.put("/user/admin*", "authc");
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/imgs/**", "anon");
        filterChainMap.put("/js/**", "anon");


// “/user/student” 开头的需要角色认证，是“admin”才允许
        filterChainMap.put("/admin/admireLogin*/**", "anon");
        filterChainMap.put("/admin/main*/**", "anon");
        filterChainMap.put("/admin/searchCorp*/**", "anon");
        filterChainMap.put("/admin/selStu*/**", "anon");
// “/user/teacher” 开头的需要权限认证，是“user:create”才允许
//        filterChainMap.put("/user/teacher*/**", "perms[\"user:create\"]");
//        filterChainMap.put("/springboot", "anon");
        // 配置logout过滤器
        filterChainMap.put("/logout", "logout");
// 设置shiroFilterFactoryBean的FilterChainDefinitionMap
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        logger.info("====shiroFilterFactoryBean注册完成====");
        return shiroFilterFactoryBean;

    }

}
