package cc.sika.common.swagger.config;

import cc.sika.common.SikaCommonSwaggerApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 吴畅
 * @创建时间 2022/12/14 - 13:47
 */
@ConditionalOnProperty(name = "swagger.enable", matchIfMissing = true)
@EnableAutoConfiguration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    public static final String BASE_PATH = "/**";


    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .select()
                // 扫描接口的位置
                .apis(RequestHandlerSelectors.basePackage("org.javastu.swaggerstu.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger")
                        .description("SpringBoot整合Swagger, 详情信息.....")
                        .version("1.0.RELEASE")
                        .contact(new Contact("小吴", "http://ironcentury.cc", "2235569493@qq.com"))
                        .license("The IronCentury License")
                        .licenseUrl("http://ironcentury.cc")
                        .build());
    }

    @Bean
    public Docket api(SwaggerProperties swaggerProperties) {
        if (!StringUtils.hasText(swaggerProperties.getBasePackage())) {
            swaggerProperties.setBasePackage(SikaCommonSwaggerApplication.class.getPackage().getName() + ".swagger.web");
        }
        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add(BASE_PATH);
        }

        List<Predicate<String>> basePathList = new ArrayList<>();
        swaggerProperties.getBasePath().forEach(path -> basePathList.add(PathSelectors.ant(path)));

        Docket docket = new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .select()
                // 扫描接口的位置
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swaggerProperties));
        return docket;
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
}
