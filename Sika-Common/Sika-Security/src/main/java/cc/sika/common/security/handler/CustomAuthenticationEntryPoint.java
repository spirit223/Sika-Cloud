package cc.sika.common.security.handler;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证失败异常处理
 *
 * @author 吴畅
 * @创建时间 2022/12/20 - 20:48
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Resource
    private ObjectMapper JSONMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setContentType("application/json");
        BaseResponse<Object> responseObj = new BaseResponse<>();
        responseObj.setSuccess(false);
        responseObj.setCode(HttpStatus.UNAUTHORIZED.getCode());
        responseObj.setMessage("用户未登录或" + authException.getMessage() + ", 请求头token为: " + request.getHeader("token"));
        String json = JSONMapper.writeValueAsString(responseObj);
        response.getWriter().write(json);
    }
}
