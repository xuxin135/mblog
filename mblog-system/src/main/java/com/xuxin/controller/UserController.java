package com.xuxin.controller;

import com.xuxin.model.Result;
import com.xuxin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return Result.success(userService.getAllUsers());
    }

    @GetMapping("/getUserById")
    public Result getUserById(int id) {
        return Result.success(userService.getUserById(id));
    }

}
