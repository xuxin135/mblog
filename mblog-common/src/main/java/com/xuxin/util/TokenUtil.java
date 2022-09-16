package com.xuxin.util;

import com.xuxin.constants.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * token管理类
 *
 * Claims结构：
 * iss: mblog
 * sub: jjwt
 * aud: userId
 * exp: 5min
 */
public class TokenUtil {

    /**
     * 创建token
     * @param userId
     * @return
     */
    public static String createToken(int userId) {
        String jws = Jwts.builder()
                .setIssuer(TokenConstants.CLAIMS_IIS)
                .setSubject(TokenConstants.CLAIMS_SUB)
                .setAudience(String.valueOf(userId))
                .setExpiration(new Date(new Date().getTime()+300000))
                .signWith(TokenConstants.SECRET_KEY)
                .compact();
        return jws;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Jws<Claims> resolveToken(String token) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(TokenConstants.SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
        }catch (Exception e) {
            throw new RuntimeException("无效令牌");
        }
        return jws;
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token) {
        Jws<Claims> jws = resolveToken(token);
        try{
            assert jws.getBody().getIssuedAt().equals(TokenConstants.CLAIMS_IIS):
                    "token发行方错误";
            assert jws.getBody().getSubject().equals(TokenConstants.CLAIMS_SUB):
                    "token主题错误";
            if(jws.getBody().getExpiration().before(new Date())){
                throw new RuntimeException("token过期，请重新登录");
            }

        } catch (NullPointerException e) {
            throw new RuntimeException("无效令牌");
        }
    }

    public static void refreshToken(Jws<Claims> jws) {

    }

}
