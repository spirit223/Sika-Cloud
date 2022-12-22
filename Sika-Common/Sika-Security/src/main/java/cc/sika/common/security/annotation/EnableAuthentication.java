package cc.sika.common.security.annotation;

import cc.sika.common.security.config.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 11:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SecurityAutoConfiguration.class})
public @interface EnableAuthentication {
}
