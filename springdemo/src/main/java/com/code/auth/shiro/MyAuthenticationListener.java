package com.code.auth.shiro;

import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Subject在登录登出时的一个监听器
 * @author ccy
 */
@Component
public class MyAuthenticationListener implements AuthenticationListener {
    @Autowired
    UserService userService;
    @Override
    public void onSuccess(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getByUsername(username);
        System.out.println("登录成功监听");
    }

    @Override
    public void onFailure(AuthenticationToken authenticationToken, AuthenticationException e) {

    }

    @Override
    public void onLogout(PrincipalCollection principalCollection) {

    }
}
