package cc.sika.common.security.controller;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.web.BaseController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 15:59
 */
@RestController
@RequestMapping("/security/test")
public class SecurityTestController extends BaseController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @GetMapping("/get/{some}")
    public BaseResponse<Object> addSome(@PathVariable("some") String some) {
        HttpSecurity bean = applicationContext.getBean(HttpSecurity.class);
        return responseSuccess(some);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
