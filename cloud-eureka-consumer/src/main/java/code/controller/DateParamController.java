package code.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import code.dto.DateParamDTO;
import code.dto.UserDTO;
import code.feign.RemoteService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试feign 发送和接受date对像
 *
 * @author ccy
 */
@RestController
public class DateParamController {
    @Autowired
    private RemoteService remoteService;

    @GetMapping("/dateParam")
    public String dateParam(){
        String dateStr = "2019-03-01";
        Date date = DateUtil.parse(dateStr);
        DateParamDTO dto = new DateParamDTO();
        dto.setDateParam(date);
        DateParamDTO dtoReturn = remoteService.dateParam(dto);
        return JSONUtil.toJsonStr(dtoReturn);
    }


    @GetMapping("/object/hello")
    public String objectHello(){
        UserDTO userDTO = new UserDTO();
        userDTO.setMsg("hello");
        userDTO.setUsername("ccy");
//        String dtoReturn = remoteService.objectHello(userDTO);
        String dtoReturn = remoteService.objectHello("ccy","hello");
        return dtoReturn;
    }
}
