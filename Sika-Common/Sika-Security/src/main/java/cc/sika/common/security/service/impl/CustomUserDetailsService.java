package cc.sika.common.security.service.impl;

import cc.sika.common.security.bean.bo.LoginUser;
import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.mapper.AuthorizationMapper;
import cc.sika.common.security.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 17:11
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AuthorizationMapper authorizationMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        List<String> s = authorizationMapper.selectPermissionByUserName(username);
        return new LoginUser(user, s);
    }
}
