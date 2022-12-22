package cc.sika.common.security;

import cc.sika.common.security.annotation.EnableAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 13:03
 */
@SpringBootApplication
@EnableAuthentication
public class SikaSecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SikaSecurityApplication.class);
        System.out.println();
    }
}
