package cc.sika.common.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 匹配 Swagger-UI 的请求路径到对应的依赖文件
 *
 * @author 吴畅
 * @创建时间 2022/12/12 - 23:31
 */
@Configuration
public class SwaggerWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:META-INF/resources/webjars/springfox-swagger-ui/");
    }
}
