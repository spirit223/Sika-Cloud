package cc.sika.common.security;

import cc.sika.common.security.config.JWTProperties;
import cc.sika.common.security.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 10:28
 */
@SpringBootTest
public class PropertiesTest {
    @Test
    void testPropertiesValue(@Autowired JWTProperties securityProperties) {
        System.out.println(securityProperties.getJwtTTL());
    }

    @Test
    void testJWTGenerator(@Autowired JWTUtils jwtUtils) {
        String s = jwtUtils.generateToken("1:小吴来哩!");
        Claims parse = jwtUtils.parse(s);
        System.out.println(parse.getSubject());
    }
}
