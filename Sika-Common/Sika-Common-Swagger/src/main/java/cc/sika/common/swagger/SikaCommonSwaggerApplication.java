package cc.sika.common.swagger;

import cc.sika.common.swagger.annotation.EnableCustomSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 吴畅
 * @创建时间 2022/12/14 - 13:06
 */
@SpringBootApplication
@EnableCustomSwagger(basePackage = "cc.sika.common.swagger.web")
public class SikaCommonSwaggerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SikaCommonSwaggerApplication.class);
    }
}
