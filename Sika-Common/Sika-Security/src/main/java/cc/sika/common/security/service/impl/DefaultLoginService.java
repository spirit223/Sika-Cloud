package cc.sika.common.security.service.impl;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.common.security.bean.bo.LoginUser;
import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.mapper.UserMapper;
import cc.sika.common.security.service.LoginService;
import cc.sika.common.security.utils.JWTUtils;
import cc.sika.common.security.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author 吴畅
 * @创建时间 2022/12/21 - 12:48
 */
@Service
public class DefaultLoginService implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JWTUtils jwtUtils;
    @Resource
    private UserMapper userMapper;


    @Override
    public BaseResponse<Object> login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        int userId = loginUser.getUser().getId();
        String token = jwtUtils.generateToken(String.valueOf(userId));
        redisUtils.setObject("loginUser: " + userId, loginUser);
        BaseResponse<Object> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setCode(HttpStatus.SUCCESS.getCode());
        HashMap<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        response.setData(tokenMap);
        response.setMessage("登录成功!");
        return response;
    }

    @Override
    public BaseResponse<Object> logout() {
        BaseResponse<Object> response = new BaseResponse<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            response.setSuccess(false);
            response.setCode(HttpStatus.NO_CONTENT.getCode());
            response.setMessage("用户未登录");
            return response;
        }
        User user = userMapper.getUserByName(authentication.getPrincipal().toString());
        // 移除 Redis 中的缓存对象, 此后的登录就需要重新走一遍查询
        redisUtils.deleteObject("loginUser: " + user.getId());
        SecurityContextHolder.getContext().setAuthentication(null);
        response.setSuccess(true);
        response.setCode(HttpStatus.SUCCESS.getCode());
        response.setMessage("注销成功");
        return response;
    }
}
