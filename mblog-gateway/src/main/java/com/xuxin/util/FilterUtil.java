package com.xuxin.util;

import com.alibaba.fastjson.JSONObject;
import com.xuxin.constants.ResultCodeConstants;
import com.xuxin.model.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

public class FilterUtil {

    /**
     * 封装返回值
     * @param exchange
     * @param result
     * @return
     */
    public static Mono<Void> res(ServerWebExchange exchange,Result result) {
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer = null;
        try {
            buffer = response.bufferFactory().wrap(JSONObject.toJSONString(result).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
