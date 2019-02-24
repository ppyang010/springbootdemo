package com.code.auth.service.impl;

import com.code.auth.dao.RoleDao;
import com.code.auth.dao.UserDao;
import com.code.auth.domain.PageBean;
import com.code.auth.domain.Role;
import com.code.auth.domain.User;
import com.code.auth.exception.CodeException;
import com.code.auth.exception.ExceptionCode;
import com.code.auth.service.UserService;
import com.code.auth.support.ModuleUtil;
import org.apache.shiro.codec.CodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/12/27.
 * @author ccy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     */
    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 获取用户列表
     *
     * @param pageBean
     * @return
     */
    @Override
    public Page<User> listUser(PageBean pageBean) {
        PageRequest pageRequest = ModuleUtil.toPageable(pageBean, null);
        Page<User> list = userDao.findAll(pageRequest);
        return list;
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    @Override
    public void save(User user) {
        //判断用户名是否存在
        Optional.ofNullable(user)
                .map(u->userDao.findByUsername(u.getUsername()))
                .filter(u->!u.getId().equals(user.getId()))
                .ifPresent(u->{
                        throw new CodeException(ExceptionCode.USERNAME_IS_EXIST);
                });
        userDao.save(user);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Integer id){
        User user = userDao.findById(id).get();
            return Optional.ofNullable(user)
                    .orElseThrow(CodeException::userinfoIsNull);
//                    .orElseThrow(()->{
//                        throw new CodeException(ExceptionCode.USERINFO_IS_NULL);
//                    });
    }

    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    /**
     * 修改用户锁定状态
     *
     * @param id
     */
    @Override
    public void changeLocked(Integer id) {
        User user = userDao.findById(id).get();
        Optional.of(user).ifPresent(u->{
            u.setLocked(u.getLocked().equals(1) ? 0 : 1);
            userDao.save(u);
        });

    }

    /**
     * 用户添加角色(list)
     *
     * @param userId
     * @param roleIdList
     */
    @Override
    public void userAddRoleList(int userId, List<Integer> roleIdList) {
        User user = userDao.findById(userId).get();
        List<Role> list = roleIdList.stream().map(roleId -> roleDao.findById(roleId).get()).collect(Collectors.toList());
        if(null == user || list.size() <= 0){
            throw new CodeException(ExceptionCode.INFO_IS_NULL);
        }
        user.getRoleSet().addAll(list);
        userDao.save(user);
    }

    /**
     * 用户删除角色(list)
     *
     * @param userId
     * @param roleIdList
     */
    @Override
    public void userDeleteRoleList(int userId, List<Integer> roleIdList) {
        User user = userDao.findById(userId).get();
        if(null == user || null == roleIdList){
            throw new CodeException(ExceptionCode.INFO_IS_NULL);
        }
        Set<Role> roleSet = user.getRoleSet();
        //查询用户有无要删除的角色
        List<Role> collect = roleSet.stream()
                .filter(role -> roleIdList.contains(role.getId()))
                .collect(Collectors.toList());
        //删除对应角色
        for (Role role : collect){
            roleSet.remove(role);
        }
        //保存变更
        userDao.save(user);

    }
}
