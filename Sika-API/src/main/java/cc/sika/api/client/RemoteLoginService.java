package cc.sika.api.client;

import cc.sika.api.bean.bo.UserInfo;
import cc.sika.api.bean.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 吴畅
 * @创建时间 2023/2/4 - 10:07
 */
@FeignClient(contextId = "remoteLoginService", value = "security-service")
public interface RemoteLoginService {

    // todo 校验时修改了JSON解析器, 默认解析器不能解析
    @GetMapping(value = "/user/check")
    BaseResponse<UserInfo> authentic(@RequestParam("token") String token);
}
