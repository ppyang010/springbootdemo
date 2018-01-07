package com.code.auth.shiro.realm;

import com.code.auth.domain.Role;
import com.code.auth.domain.User;
import com.code.auth.service.PermissionsService;
import com.code.auth.service.RoleService;
import com.code.auth.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by s on 2017/9/26.
 * @author ccy
 */
@Component
public class LoginRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionsService permissionsService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = roleService.listRolesByUsername(username);
        Set<String> permissions = permissionsService.listPermissionsByUsername(username);
        return null;
    }

    /**
     * Authentication（认证）（认证身份）（登陆）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUsername(username);
        if(null == user){
            //没找到帐号
            throw new UnknownAccountException();
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            //帐号锁定
            throw new LockedAccountException();
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                user.getUsername(),
                //密码
                user.getPassword(),
                //salt=username+salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

//    @Autowired
//    UserService userService;
//    //授权 subject进行权限或者角色判断的时候就会执行这里
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String username = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        Set<String> temp=userService.findRoles(username);
//        simpleAuthorizationInfo.setRoles(temp);
//        temp=userService.findPermissions(username);
//        simpleAuthorizationInfo.setStringPermissions(temp);
//
//        return simpleAuthorizationInfo;
//    }
//    //Authentication（认证）（认证身份）（登陆）
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username= (String) authenticationToken.getPrincipal();
//        User user=userService.getUserByUsername(username);
//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
//        );
//        return authenticationInfo;
//    }
}
