package cc.sika.common.security.controller;

import cc.sika.api.bean.bo.UserInfo;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.service.LoginService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 吴畅
 * @创建时间 2022/12/21 - 13:13
 */
@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Resource
    private LoginService loginService;

    @CrossOrigin
    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @CrossOrigin
    @PostMapping("/logout")
    public BaseResponse<Object> logout() {
        return loginService.logout();
    }

    @GetMapping("/check")
    public BaseResponse<UserInfo> isLogin(@RequestParam("token") String token) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return responseFail(HttpStatus.UNAUTHORIZED);
        }
        UserInfo userInfo = loginService.checkUserInfo(token);
        return responseSuccess(userInfo, "");
    }
}
