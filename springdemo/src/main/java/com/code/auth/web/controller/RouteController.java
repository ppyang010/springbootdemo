package com.code.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class RouteController {

    @RequestMapping(value = {"/index","/"})
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/admin/login")
    public String login (Model model){
        return "login";
    }
    @RequestMapping("/p/permission/{url}")
    public String permissionList(@PathVariable(name = "url") String url){
        return "/page/permission/"+url;
    }

    @RequestMapping("/p/user/{url}")
    public String userList(@PathVariable(name = "url") String url){
        return "/page/user/"+url;
    }

    @RequestMapping("/p/role/{url}")
    public String roleList(@PathVariable(name = "url") String url){
        return "/page/role/"+url;
    }

}
