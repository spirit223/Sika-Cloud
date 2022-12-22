package cc.sika.common.security.handler;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 授权失败, 权限不足的访问异常处理
 *
 * @author 吴畅
 * @创建时间 2022/12/21 - 12:31
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    private ObjectMapper JSONMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        BaseResponse<Object> responseObj = new BaseResponse<>();
        responseObj.setSuccess(false);
        responseObj.setCode(HttpStatus.FORBIDDEN.getCode());
        responseObj.setMessage("权限不足");
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(JSONMapper.writeValueAsString(responseObj));
    }
}
