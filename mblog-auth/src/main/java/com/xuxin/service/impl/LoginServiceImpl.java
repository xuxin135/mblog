package com.xuxin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xuxin.constants.TokenConstants;
import com.xuxin.model.LoginForm;
import com.xuxin.model.Result;
import com.xuxin.dto.User;
import com.xuxin.service.ILoginService;
import com.xuxin.util.RedisUtil;
import com.xuxin.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result login(LoginForm loginForm) {

        //验证用户名密码，根据用户名查询用户
        User user = new User(1,"xuxin1","111111",true);
        if(user == null) {
            return Result.failed("用户名或密码错误！");
        }

        //验证用户是否可用
        if(!user.isActive()){
            return Result.failed("用户已禁用，请联系管理员！");
        }

        //验证用户成功后，创建token
        String token = TokenUtil.createToken(user.getId());

        //将token保存到redis里面，key为token:userId，value为token字符串
        //过期时间为10分钟

        String key = TokenConstants.TOKEN_HEAD + user.getId();
        redisUtil.setExpireByMin(key, token, TokenConstants.TOKEN_EXPIRE_TIME);

        JSONObject object = new JSONObject();
        object.put("userId",user.getId());
        object.put("token",token);
        return Result.success(object);
    }
}
