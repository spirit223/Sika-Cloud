package cc.sika.filter;

import cc.sika.api.bean.bo.UserInfo;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.client.RemoteLoginService;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 吴畅
 * @创建时间 2023/2/4 - 10:21
 */
@Component("authenticFilter")
public class AuthenticFilter implements GlobalFilter, Ordered {
    @Resource
    private RemoteLoginService securityService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 检查 Token, 如果 Token 校验通过, 放行
        String token = request.getHeaders().getFirst("token");
        if (!StringUtils.hasText(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        Future<BaseResponse<UserInfo>> submit = singleThreadPool.submit(() -> securityService.authentic(token));
        BaseResponse<UserInfo> userInfoBaseResponse;
        try {
            userInfoBaseResponse = submit.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if (userInfoBaseResponse.getCode() == cc.sika.api.common.HttpStatus.UNAUTHORIZED.getCode()) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 校验通过添加用户名和昵称到header中
        UserInfo userInfo = userInfoBaseResponse.getData();
        ServerHttpRequest gateway = exchange.getRequest().mutate()
                .header("X-User-Name", userInfo.getUsername())
                .header("X-Nick-Name", userInfo.getNickname())
                .build();
        ServerWebExchange build = exchange.mutate().request(gateway).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return -100;
    }


//    @Override
//    public GatewayFilter apply(Object config) {
//        return (exchange, chain) -> {
//            ServerHttpResponse response = exchange.getResponse();
//            String token = exchange.getRequest().getHeaders().getFirst("token");
//            if (!StringUtils.hasText(token)) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                return response.setComplete();
//            }
//            BaseResponse<UserInfo> authentic = securityService.authentic(token);
//            if (authentic.getCode() == cc.sika.api.common.HttpStatus.UNAUTHORIZED.getCode()) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                return response.setComplete();
//            }
//            UserInfo userInfo = authentic.getData();
//            ServerHttpRequest build = exchange.getRequest().mutate()
//                    .header("X-User-Name", userInfo.getUsername())
//                    .header("X-Nick-Name", userInfo.getNickname())
//                    .build();
//            ServerWebExchange build1 = exchange.mutate().request(build).build();
//            return chain.filter(build1);
//        };
//    }
}
