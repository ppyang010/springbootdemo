package com.code.auth.web.controller;

import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import com.code.auth.support.Consts;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("/doLogin")
    public String doLogin(User user, HttpServletRequest request){
        System.out.println("doLogin");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try{
            subject.login(token);//会跳到我们自定义的realm中
            subject.getSession().removeAttribute("user");
            user = userService.getByUsername(user.getUsername());
            subject.getSession().setAttribute("userInfo",user);
//            User user1 = (User) subject.getPrincipal();
//            User sessionUser = (User) request.getSession().getAttribute("user");
            return "redirect:/index";
        }catch(Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "redirect:" + Consts.URL.ADMIN_LOGIN_URL;
        }
    }

}
