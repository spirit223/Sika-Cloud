package cc.sika.common.swagger.annotation;

import cc.sika.common.swagger.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author 吴畅
 * @创建时间 2022/12/12 - 23:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SwaggerAutoConfiguration.class)
@ComponentScan
public @interface EnableCustomSwagger2 {
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] value() default {};

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] basePackages() default {};
}
