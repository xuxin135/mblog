package com.xuxin.service.impl;

import com.xuxin.dto.User;
import com.xuxin.mappers.UserMapper;
import com.xuxin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSeriviceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user, int[] roleIds) {
        //添加用户
        userMapper.addUser(user);
        userMapper.addUserRole(user.getId(),roleIds);
    }

    @Override
    @Transactional
    public void updateUser(User user, int[] roleIds) {
        //更新用户信息
        userMapper.updateUser(user);
        //删除原来角色信息
        userMapper.deleteUserRoleByUser(user.getId());
        //插入修改的角色信息
        userMapper.addUserRole(user.getId(),roleIds);
    }

    @Override
    @Transactional
    public void deleteUsers(int[] ids) {
        userMapper.deleteUsers(ids);
    }
}
