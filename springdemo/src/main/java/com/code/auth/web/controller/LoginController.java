package com.code.auth.web.controller;

import com.code.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping("/doLogin")
    public String doLogin(){
        return "index";
    }

}
