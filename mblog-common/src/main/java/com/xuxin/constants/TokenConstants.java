package com.xuxin.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenConstants {

    /**
     * 密钥
     */
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * token发行方
     */
    public static final String CLAIMS_IIS = "mblog";

    /**
     * token主题
     */
    public static final String CLAIMS_SUB = "jjwt";

    /**
     * token过期时间
     */
    public static final int TOKEN_EXPIRE_TIME = 10;

    /**
     * 保存在redis中的token头
     */
    public static final String TOKEN_HEAD = "token:";


}
