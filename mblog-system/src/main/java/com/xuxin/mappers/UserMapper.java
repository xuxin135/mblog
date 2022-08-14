package com.xuxin.mappers;

import com.xuxin.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void addUserRole(int userId, int[] RoleId);

    void deleteUserRoleByUser(int userId);

    void updateUser(User user);

    void deleteUsers(int[] ids);
}
