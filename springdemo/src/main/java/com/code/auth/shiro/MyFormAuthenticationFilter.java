package com.code.auth.shiro;

import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
@Component
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{

    @Autowired
    UserService userService;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String principal = (String) subject.getPrincipal();
        User user = userService.getByUsername(principal);
        subject.getSession().setAttribute("userInfo",user);

        return super.onLoginSuccess(token, subject, request, response);
    }
}
