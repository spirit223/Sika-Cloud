package cc.sika.common.security.controller;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.common.security.bean.bo.RegisterUserBO;
import cc.sika.common.security.bean.vo.RegisterVerifyVO;
import cc.sika.common.security.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户注册相关接口
 *
 * @author 吴畅
 * @创建时间 2023/3/4 - 19:28
 */
@RestController
@RequestMapping("/register")
@CrossOrigin("*")
public class RegisterController extends BaseController {

    @Resource
    private RegisterService registerService;

    @PostMapping("/add")
    public BaseResponse<RegisterVerifyVO> registerUser(@RequestBody RegisterUserBO registerUserBO) {
        if (registerService.checkUser(registerUserBO.getUsername())) {
            return responseFail(HttpStatus.CONFLICT, "用户名已存在");
        }

        return responseSuccess();
    }

    @GetMapping("/verify/{name}")
    public BaseResponse<Boolean> verifyUserName(@PathVariable("name") String name) {
        if (registerService.checkUser(name)) {
            System.out.println("校验通过");
            return responseSuccess(true);
        }
        return responseFail(HttpStatus.CONFLICT, "用户名已存在");
    }
}
