package com.xuxin.controller;

import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;
import com.xuxin.remote.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private RemoteUserService remoteUserService;

    @PostMapping("/login")
    public Result login(LoginForm loginForm) {
        return remoteUserService.login(loginForm);
    }
}
