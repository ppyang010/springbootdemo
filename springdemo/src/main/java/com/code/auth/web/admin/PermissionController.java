package com.code.auth.web.admin;

import com.code.auth.domain.PageBean;
import com.code.auth.domain.Permissions;
import com.code.auth.domain.PlatformReturn;
import com.code.auth.service.PermissionsService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    PermissionsService permissionsService;

    @GetMapping("/list")
    public PlatformReturn list(PageBean pageBean){
        Page<Permissions> page = permissionsService.listPermission(pageBean);
        return  PlatformReturn.success().setItems(page.getContent()).setPageBean(page);
    }

    @DeleteMapping("/{id}")
    public PlatformReturn delete(@PathVariable("id") int id){
        permissionsService.deleteById(id);
        return PlatformReturn.success();
    }
}
