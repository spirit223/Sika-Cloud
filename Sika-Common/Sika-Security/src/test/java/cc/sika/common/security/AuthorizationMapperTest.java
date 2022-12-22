package cc.sika.common.security;

import cc.sika.common.security.mapper.AuthorizationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 17:06
 */
@SpringBootTest
public class AuthorizationMapperTest {

    @Resource
    private AuthorizationMapper authorizationMapper;

    @Test
    void testGetPermissions() {
        List<String> stringList = authorizationMapper.selectPermissionByUserId(11);
        System.out.println(stringList);

        List<String> stringListByName = authorizationMapper.selectPermissionByUserName("测试更新用户2");
        System.out.println(stringListByName);
    }
}
