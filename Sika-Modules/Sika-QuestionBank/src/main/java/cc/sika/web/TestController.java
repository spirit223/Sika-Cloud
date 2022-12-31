package cc.sika.web;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 15:59
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    @GetMapping("/get/{some}")
    public BaseResponse<Object> addSome(@PathVariable("some") String some) {
        return responseSuccess(some);
    }
}
