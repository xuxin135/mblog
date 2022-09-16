package com.xuxin.fallback;

import com.xuxin.constants.ResultCodeConstants;
import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;
import com.xuxin.remote.RemoteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 用户远程服务调用失败
 */
public class UserFallbackFactory implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(UserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable cause) {
        log.error("用户接口调用失败：" + cause.getMessage());
        return new RemoteUserService() {

            @Override
            public Result login(LoginForm form) {
                return Result.failed(ResultCodeConstants.SERVICE_ERROR, cause.getMessage());
            }

        };
    }
}
