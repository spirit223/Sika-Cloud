package cc.sika.common.swagger.annotation;

import cc.sika.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 开启自动配置Swagger-UI注解, 在启动类上添加该注解以自动生成接口文档
 * @author 吴畅
 * @创建时间 2022/12/12 - 23:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SwaggerAutoConfiguration.class)
@ComponentScan
public @interface EnableCustomSwagger {
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] value() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] basePackages() default {};

    /**
     * 指定接口所在的包名
     *
     * @return
     */
    String basePackage() default "";
}
