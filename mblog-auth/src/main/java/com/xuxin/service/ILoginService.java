package com.xuxin.service;

import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;

public interface ILoginService {
    Result login(LoginForm loginForm);
}
