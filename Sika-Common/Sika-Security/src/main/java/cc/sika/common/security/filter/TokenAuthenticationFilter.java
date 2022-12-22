package cc.sika.common.security.filter;

import cc.sika.common.security.bean.bo.LoginUser;
import cc.sika.common.security.exception.InvalidTokenException;
import cc.sika.common.security.utils.JWTUtils;
import cc.sika.common.security.utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
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
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JWTUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private ObjectMapper JSONMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        /* 如果没有 token 直接放行不进行校验 */
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = null;
        try {
            claims = jwtUtils.parse(token);
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
        filterChain.doFilter(request, response);
    }
}
