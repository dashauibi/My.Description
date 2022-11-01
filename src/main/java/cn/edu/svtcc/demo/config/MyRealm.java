package cn.edu.svtcc.demo.config;

import cn.edu.svtcc.demo.model.AdminUser;
import cn.edu.svtcc.demo.service.Imp.AdminServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;


public class MyRealm extends AuthorizingRealm {
    @Resource
    AdminServiceImp admireServiceImp;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new
                SimpleAuthorizationInfo();
// 给该用户设置角色，角色信息存在t_role表中取

        authorizationInfo.setRoles((Set<String>) admireServiceImp.getRoles(username));
// 给该用户设置权限，权限信息存在t_permission表中取
        authorizationInfo.setStringPermissions((Set<String>) admireServiceImp.getPermission(username));
        return authorizationInfo;
    }


//    public void exime(String username1){
//
//
//    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据token获取用户名，如果您不知道该该token怎么来的，先可以不管，下文会解释
        String username = (String) authenticationToken.getPrincipal();
// 根据用户名从数据库中查询该用户
        AdminUser user = admireServiceImp.findAccount(username);
        if (user != null) {
// 把当前用户存到session中
            SecurityUtils.getSubject().getSession().setAttribute("static/user", user);
// 传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), "myRealm");
            return authcInfo;
        } else {
            return null;
        }

    }

}
