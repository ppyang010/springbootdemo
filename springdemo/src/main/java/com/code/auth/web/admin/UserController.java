package com.code.auth.web.admin;

import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public String listUser(HttpServletRequest request){
//        List<User> list = userService.listUser();
        return null;
    }
}
