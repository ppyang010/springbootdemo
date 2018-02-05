package com.code.auth.web;

import com.code.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

}
