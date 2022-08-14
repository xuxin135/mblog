package com.xuxin.mappers;

import com.xuxin.dto.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();

    Menu getMenuById(int id);

    void addMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(int[] ids);
}
