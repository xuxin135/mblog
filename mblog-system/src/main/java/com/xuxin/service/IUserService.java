package com.xuxin.service;

import com.xuxin.dto.Role;
import com.xuxin.dto.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user, int[] roleIds);

    void updateUser(User user, int[] roleIds);

    void deleteUsers(int[] ids);
}
