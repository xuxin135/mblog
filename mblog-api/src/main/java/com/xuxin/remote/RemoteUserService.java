package com.xuxin.remote;

import com.xuxin.constants.ServiceNameConstants;
import com.xuxin.fallback.UserFallbackFactory;
import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 远程用户服务
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.MBLOG_AUTH, fallbackFactory = UserFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 登录接口
     * @param form 登录表单
     * @return
     */
    @GetMapping("/auth/login")
    Result login(LoginForm form);


}
