package cc.sika.common.security.utils;

import cc.sika.common.security.config.JWTProperties;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT生成和解析的工具类
 *
 * @author 吴畅
 * @创建时间 2022/12/20 - 10:33
 */
@Component
public class JWTUtils {
    @Resource
    private JWTProperties jwtProperties;

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * JWT构建器
     *
     * @param subject Token 内容主体
     * @param uuid    构建器id, 未指定时使用 UUID 自动生成
     * @param timeTTL 过期时间, 未指定则使用配置
     * @return JWT构建器
     */
    public JwtBuilder getJwtBuilder(String subject, Long timeTTL, String uuid) {
        long nowMills = System.currentTimeMillis();
        long expMills = nowMills + jwtProperties.getJwtTTL();
        if (timeTTL != null) {
            expMills = nowMills + timeTTL;
        }

        Date now = new Date(nowMills);
        Date expiration = new Date(expMills);
        String id = getUUID();
        if (StringUtils.hasText(uuid)) {
            id = uuid;
        }
        return Jwts.builder().setId(id)
                // 主体信息
                .setSubject(subject)
                // 签发人
                .setIssuer(jwtProperties.getIssuer())
                // 签发时间
                .setIssuedAt(now)
                // 签名算法和密钥, 密钥使用Base64解码字符串作为加密的密钥
                .signWith(SignatureAlgorithm.HS256, encipherSecret())
                // 设置过期时间
                .setExpiration(expiration);
    }

    /**
     * 加密明文的密钥, 见 {@link SecretKeySpec}
     *
     * @return 使用Base64解码明文后生成的字节数组作为密钥生成 SecretKeySpec
     */
    public SecretKeySpec encipherSecret() {
        byte[] encodedKey = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public String generateToken(String subject) {
        return generateToken(subject, null);
    }

    public String generateToken(String subject, Long timeTTL) {
        return generateToken(subject, timeTTL, null);
    }

    public String generateToken(String subject, Long timeTTL, String id) {
        return getJwtBuilder(subject, timeTTL, id).compact();
    }

    public Claims parse(final String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(encipherSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
