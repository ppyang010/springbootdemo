package com.code.auth.web.admin;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.Permissions;
import com.code.auth.domain.PlatformReturn;
import com.code.auth.service.PermissionsService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    PermissionsService permissionsService;

    @GetMapping("/list")
    public PlatformReturn list(PageBean pageBean){
        Page<Permissions> page = permissionsService.listPermission(pageBean);
        page.getContent().stream().forEach(p->p.getRoleSet());
        return  PlatformReturn.success().setItems(page.getContent()).setPageBean(page);
    }



    @DeleteMapping("/{id}")
    public PlatformReturn delete(@PathVariable("id") int id){
        permissionsService.deleteById(id);
        return PlatformReturn.success();
    }

    /**
     * 添加and更新
     * @param id
     * @param permission
     * @param description
     * @param available
     * @param request
     * @return
     */
    @PostMapping
    public PlatformReturn save(Integer id , String permission, String description, @RequestParam(name="available",required = false ,defaultValue = "1") int available , HttpServletRequest request){
        Permissions permissions = new Permissions(id, permission, description, available,null);
        permissionsService.save(permissions);
        return PlatformReturn.success();
    }

    @GetMapping("{id}")
    public PlatformReturn detail(@PathVariable("id") int id){
        Permissions p = permissionsService.findPermissionById(id);
        return PlatformReturn.success().setItem(p);
    }


    /**
     * 修改权限可用状态
     * @return
     */
    @PostMapping("{id}/available")
    public PlatformReturn updateAvailable(@PathVariable("id") Integer id){
        permissionsService.changeAvailable(id);
        return PlatformReturn.success();
    }


}
