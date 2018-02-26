package com.code.auth.web.admin;


import cn.hutool.core.collection.CollUtil;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.PlatformReturn;
import com.code.auth.domain.Role;
import com.code.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
     * @return
     */
    @PostMapping
    public PlatformReturn save(Integer id , String role, String description, @RequestParam(name="available",required = false ,defaultValue = "1") int available , HttpServletRequest request){
        roleService.save(new Role(id,role,description,available,null));
        return PlatformReturn.success();
    }

    @DeleteMapping("/{id}")
    public PlatformReturn delete(@PathVariable("id") int id){
        roleService.deleteRoleById(id);
        return PlatformReturn.success();
    }

    @GetMapping("/{id}")
    public PlatformReturn detail (@PathVariable("id") int id){
        Role role = roleService.getRoleById(id);
        return PlatformReturn.success().setItem(role);
    }

    /**
     * 修改角色可用状态
     * @return
     */
    @PostMapping("{id}/available")
    public PlatformReturn updateAvailable(@PathVariable("id") Integer id){
        roleService.changeAvailable(id);
        return PlatformReturn.success();
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @PostMapping("/permissions/add")
    public PlatformReturn roleAddPermission(int roleId, int permissionId){
//        roleService.roleAddPermission(roleId,permissionId);
        roleService.roleAddPermissionList(roleId, Arrays.asList(permissionId));
        return PlatformReturn.success();
    }

    /**
     * 为角色删除权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @PostMapping("/permissions/delete")
    public PlatformReturn roleDeletePermission(int roleId, int permissionId){
        roleService.roleDeletePermission(roleId,permissionId);
        return PlatformReturn.success();
    }


    /**
     * 为角色添加权限(list)
     * @param roleId
     * @param permissionIdList
     * @return
     */
    @PostMapping("/permissions/add/list")
    public PlatformReturn roleAddPermissionList(int roleId, @RequestParam(required = false, value = "permissionIdList[]", defaultValue = "") List<Integer> permissionIdList){
        roleService.roleAddPermissionList(roleId,permissionIdList);
        return PlatformReturn.success();
    }


    /**
     * 为角色删除权限(list)
     * @param roleId
     * @param permissionIdList
     * @return
     */
    @PostMapping("/permissions/delete/list")
    public PlatformReturn roleDeletePermissionList(int roleId, @RequestParam(required = false, value = "permissionIdList[]") Integer[] permissionIdList){
        roleService.roleDeletePermissionList(roleId, Arrays.asList(permissionIdList));
        return PlatformReturn.success();
    }


}
