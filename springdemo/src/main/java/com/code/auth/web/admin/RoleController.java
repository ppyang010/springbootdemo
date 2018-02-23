package com.code.auth.web.admin;


import com.code.auth.domain.PageBean;
import com.code.auth.domain.PlatformReturn;
import com.code.auth.domain.Role;
import com.code.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public PlatformReturn list(PageBean pageBean, HttpServletRequest request){
        Page<Role> roles = roleService.listRole(pageBean);
        return PlatformReturn.success().setItems(roles.getContent()).setPageBean(roles);
    }

    /**
     * 保存（添加and更新）
     * @param role
     * @return
     */
    public PlatformReturn save(Role role){
        roleService.save(role);
        return PlatformReturn.success();
    }

}
