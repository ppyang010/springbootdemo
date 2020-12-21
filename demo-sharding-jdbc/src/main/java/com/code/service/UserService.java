package com.code.service;

import com.code.entity.UserInfo;
import com.code.mapper.UserInfoMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserInfoMapper userInfoMapper;

    public static Long userId = 1L;

    public void insert() {
        for (int i = 1; i <= 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount("account" + i);
            userInfo.setPassword("password" + i);
            userInfo.setUserName("name" + i);
            userId++;
            userInfoMapper.insert(userInfo);
        }
    }

    public UserInfo getUserInfoByUserId(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public UserInfo getUserInfoByUserIdForMaster(Long id) {
        //设置强制路由到主库  可以封装成切面注解
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        try {
            return userInfoMapper.selectByPrimaryKey(id);
        } finally {
            //关闭
            hintManager.close();
        }

    }

    public List<UserInfo> selectByRange(Long firstId, Long lastId) {
        return userInfoMapper.selectByRange(firstId, lastId);
    }

    public void updateAndSelect(long id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        userInfo.setUserName("updateAndSelect");
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        userInfo = userInfoMapper.selectByPrimaryKey(id);
    }
}
