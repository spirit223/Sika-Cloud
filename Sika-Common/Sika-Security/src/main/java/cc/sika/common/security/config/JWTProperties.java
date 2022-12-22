package cc.sika.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 10:22
 */
@Configuration
@ConfigurationProperties(prefix = "cc.token")
@Data
public class JWTProperties {
    /**
     * JWT 密钥
     */
    private String secretKey;

    /**
     * 有效期
     */
    private Long jwtTTL;

    private String issuer;
}
