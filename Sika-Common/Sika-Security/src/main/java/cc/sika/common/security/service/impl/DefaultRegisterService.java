package cc.sika.common.security.service.impl;

import cc.sika.common.security.service.RegisterService;
import org.springframework.stereotype.Service;

/**
 * @author 吴畅
 * @创建时间 2023/3/4 - 19:34
 */
@Service
public class DefaultRegisterService implements RegisterService {
    @Override
    public boolean checkUser(String username) {
        return false;
    }
}
