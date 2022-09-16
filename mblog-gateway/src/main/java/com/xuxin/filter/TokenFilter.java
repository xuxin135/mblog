package com.xuxin.filter;

import com.xuxin.constants.ResultCodeConstants;
import com.xuxin.constatnts.PathConstants;
import com.xuxin.model.Result;
import com.xuxin.util.FilterUtil;
import com.xuxin.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * token过滤器，验证请求是否携带token
 * 除登录相关请求外，所有请求都需要携带token
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * 过滤器排除的路径
     */
    @Value("${gateway.global.exclude-url}")
    private List<String> url;

    /**
     * 静态资源路径
     */
    @Value("${gateway.global.static-url}")
    private String staticUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //需要排除的路径直接通过过滤器
        if(url.contains(exchange.getRequest().getPath().toString())) {
            return chain.filter(exchange);
        }

        //请求路径
        String path = exchange.getRequest().getPath().toString();

        //登录、注册、登出请求跳过过滤器
        if(path.contains(PathConstants.LOGIN_PAGE) ||
            path.contains(PathConstants.REGISTER_PAGE) ||
            path.contains(PathConstants.LOGOUT_PAGE)) {
            return chain.filter(exchange);
        }

        //静态资源跳过过滤器

        if(path.contains(staticUrl)){
            return chain.filter(exchange);
        }

        //验证请求是否携带token
        if (!exchange.getRequest().getHeaders().containsKey("token")){
            //请求不带token
            String res = "无token，请登录获取token！---------->";
            log.error(res + exchange.getRequest().getPath());
            return FilterUtil.res(exchange,Result.failed(ResultCodeConstants.NO_TOKEN, res));
        }else {
            //请求带token
            String token = exchange.getRequest().getHeaders().get("token").toString();
            try {
                //验证token
                TokenUtil.verify(token);
            } catch (Exception e) {
                return FilterUtil.res(exchange,Result.failed(ResultCodeConstants.ERROR_TOKEN,e.getMessage()));
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
