package cc.sika.common.swagger.config;

import cc.sika.common.swagger.annotation.EnableCustomSwagger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * SwaggerUI自动配置类,
 * @author 吴畅
 * @创建时间 2022/12/14 - 13:47
 */
@ConditionalOnProperty(name = "swagger.enable", matchIfMissing = true)
//@EnableAutoConfiguration
@EnableConfigurationProperties(SwaggerProperties.class)
@Slf4j
public class SwaggerAutoConfiguration implements ApplicationContextAware {

    public static final String BASE_PATH = "/**";


    @Bean
    public Docket api(SwaggerProperties swaggerProperties) {
        /* 没有配置或在注解指定包路径, 扫描对应启动类实现接口包匹配 */
        if (!StringUtils.hasText(swaggerProperties.getBasePackage())) {
            String packageByAnnotation = getPackageByAnnotation();
            if (!StringUtils.hasText(packageByAnnotation)) {
                /* 未通过注解指定包名 */
                log.warn("已开启Swagger自动生成接口文档, 但未指定接口扫描包, 即将自动配置接口包路径");
                String autoGeneratedPackageName = "";
                for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
                    if (beanDefinitionName.endsWith("Application")) {
                        autoGeneratedPackageName = applicationContext.getBean(beanDefinitionName).getClass().getPackage().getName() + ".web";
                        break;
                    }
                }
                swaggerProperties.setBasePackage(autoGeneratedPackageName);
                log.warn("自动生成扫描包, 包名为 ==> {}", autoGeneratedPackageName);
            } else {
                /* 通过注解指定包名 */
                log.info("Swagger-UI扫描包: {}", packageByAnnotation);
                swaggerProperties.setBasePackage(packageByAnnotation);
            }
        }
        if (swaggerProperties.getBasePath().isEmpty()) {
            log.warn("已开启Swagger自动生成接口文档, 但未指定过滤路径, 自动配置过滤路径为{}", BASE_PATH);
            swaggerProperties.getBasePath().add(BASE_PATH);
        }

        List<Predicate<String>> basePathList = new ArrayList<>();
        swaggerProperties.getBasePath().forEach(path -> basePathList.add(PathSelectors.ant(path)));
        List<Predicate<String>> excludePathList = new ArrayList<>();
        swaggerProperties.getExcludePath().forEach(excludePath -> excludePathList.add(PathSelectors.ant(excludePath)));

        ApiSelectorBuilder apis = new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .select()
                // 扫描接口的位置
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()));
        /* 添加指定的请求路径 */
        basePathList.forEach(apis::paths);
        /* 删除指定的请求路径 */
        excludePathList.forEach(excludePathPredicate -> apis.paths(excludePathPredicate.negate()));
        return apis
                .build()
                .securitySchemes(getSecuritySchemes())
                .securityContexts(allSecurityContext())
                .apiInfo(apiInfo(swaggerProperties));
    }

    private List<SecurityScheme> getSecuritySchemes() {
        ApiKey apiKey = new ApiKey("token", "authorization", "header");
        return Collections.singletonList(apiKey);
    }

    /**
     * 和 securitySchemes 绑定的安全上下文
     */
    private List<SecurityContext> allSecurityContext() {
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                // todo forPath 使用 operationSelector 替换
                .forPaths(PathSelectors.any())
                .build());
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "全局需要授权");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        /* reference 和 ApiKey 的 name 参数对应 */
        return Collections.singletonList(new SecurityReference("token", authorizationScopes));
    }


    /**
     * 填充 ApiInfo
     *
     * @param swaggerProperties 配置信息对象
     * @return ApiInfo
     */
    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .contact(
                        new Contact(swaggerProperties.getContact().getName(),
                                swaggerProperties.getContact().getUrl(),
                                swaggerProperties.getContact().getEmail())
                )
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .build();
    }

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 从注解值中获取包路径
     */
    private String getPackageByAnnotation() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(EnableCustomSwagger.class);
        if (beansWithAnnotation.isEmpty()) {
            return "";
        }
        EnableCustomSwagger customSwagger2 = null;
        for (String key : beansWithAnnotation.keySet()) {
            customSwagger2 = beansWithAnnotation.get(key).getClass().getAnnotation(EnableCustomSwagger.class);
            break;
        }
        return customSwagger2.basePackage();
    }
}
