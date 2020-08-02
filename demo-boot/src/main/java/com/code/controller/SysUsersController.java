package com.code.controller;


import cn.hutool.json.JSONUtil;
import com.code.entity.SysUsers;
import com.code.service.ISysUsersService;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ccy
 * @since 2020-08-02
 */
@RestController
@RequestMapping("/sys-users")
public class SysUsersController {
    @Autowired
    private ISysUsersService iSysUsersService;

    @GetMapping("/getOne")
    public String getOne(){
        SysUsers byId = iSysUsersService.getById(1);
        return JSONUtil.toJsonStr(byId);
    }

}
