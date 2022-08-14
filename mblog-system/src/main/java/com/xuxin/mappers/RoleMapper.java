package com.xuxin.mappers;

import com.xuxin.dto.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> getAllRoles();

    Role getRoleById(int id);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRoles(int[] ids);
}
