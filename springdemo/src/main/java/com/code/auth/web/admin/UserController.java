package com.code.auth.web.admin;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.PlatformReturn;
import com.code.auth.domain.User;
import com.code.auth.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public PlatformReturn listUser(PageBean pageBean, HttpServletRequest request){
        Page<User> page = userService.listUser(pageBean);
        return PlatformReturn.success().setPageBean(page).setItems(page.getContent());
    }

    @PostMapping
    public PlatformReturn add(String username, String password, @RequestParam(name="locked",required = false ,defaultValue = "0") int locked , HttpServletRequest request){
        System.out.println("add");
        System.out.println(username+password+locked);
        return PlatformReturn.success();
    }
}
