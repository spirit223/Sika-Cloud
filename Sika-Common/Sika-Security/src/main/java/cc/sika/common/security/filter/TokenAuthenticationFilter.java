package cc.sika.common.security.filter;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.common.security.bean.bo.LoginUser;
import cc.sika.common.security.exception.InvalidTokenException;
import cc.sika.common.security.utils.JWTUtils;
import cc.sika.common.security.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 请求拦截过滤器, 将请求拦截后从缓存获取已经登录用户信息, 避免频繁查询数据库
 *
 * @author 吴畅
 * @创建时间 2022/12/21 - 15:09
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JWTUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private ObjectMapper JSONMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("前置过滤器开始缓存身份校验");
        String token = request.getHeader("token");
        /* 如果没有 token 直接放行不进行校验 */
        if (!StringUtils.hasText(token)) {
            log.info("未发现token, 即将进行登录");
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims;
        try {
            log.info("token解析开始");
            claims = jwtUtils.parse(token);
        } catch (ExpiredJwtException expiredException) {
            // Token过期处理
            BaseResponse<Object> failResponse = new BaseResponse<>();
            failResponse.setSuccess(false);
            failResponse.setCode(HttpStatus.FORBIDDEN.getCode());
            failResponse.setMessage("token已过期 " + expiredException.getMessage());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(JSONMapper.writeValueAsString(failResponse));
            return;
        } catch (SignatureException e) {
            throw new RuntimeException("Token非法");
        }
        String userId = claims.getSubject();
        /* token非法或失效 */
        if (userId == null) {
            throw new InvalidTokenException();
        }
        /* 从redis中获取用户信息 */
        String redisUserKey = "loginUser: " + userId;
        Object object = redisUtils.getObject(redisUserKey);
        if (Objects.isNull(object)) {
            log.info("缓存未命中, 重新登录");
            filterChain.doFilter(request, response);
            return;
        }
        LoginUser loginUser = JSONMapper.readValue(JSONMapper.writeValueAsString(object), LoginUser.class);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(), loginUser.getPassword(),
                        loginUser.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("身份信息校验通过");
        filterChain.doFilter(request, response);
    }
}
