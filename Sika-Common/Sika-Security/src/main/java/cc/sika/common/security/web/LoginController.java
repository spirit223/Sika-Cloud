package cc.sika.common.security.web;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 吴畅
 * @创建时间 2022/12/21 - 13:13
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Object> logout() {
        return loginService.logout();
    }
}
