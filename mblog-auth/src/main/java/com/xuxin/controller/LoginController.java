package com.xuxin.controller;

import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;
import com.xuxin.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm) {
        //参数校验
        if(!StringUtils.hasLength(loginForm.getUsername())){
            return Result.failed("用户名不能为空！");
        }
        if(!StringUtils.hasLength(loginForm.getPassword())) {
            return Result.failed("密码不能为空！");
        }
        return loginService.login(loginForm);
    }

    @GetMapping("/login")
    public String testLogin() {
        return "login success";
    }

}
