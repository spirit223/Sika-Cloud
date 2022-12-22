package cc.sika.common.security.config;

import cc.sika.common.security.filter.TokenAuthenticationFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 11:06
 */
@EnableAutoConfiguration
@ConditionalOnProperty(value = "security.enable", matchIfMissing = true)
@EnableConfigurationProperties(SecurityConfigProperties.class)
@MapperScan(basePackages = "cc.sika.common.security.mapper")    // 作为依赖时扫描本依赖 mapper 接口
public class SecurityAutoConfiguration {
    @Autowired
    private SecurityConfigProperties securityConfigProperties;
    @Resource
    private AuthenticationEntryPoint customAuthenticationEntryPoint;
    @Resource
    private AccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 关闭 CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 关闭 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> {
                    securityConfigProperties.getMatches().forEach(
                            p4p -> {
                                List<String> permissionList = p4p.getPermission();
                                /* 未定义权限或权限空字符串匹配 */
                                if (Objects.isNull(permissionList) || permissionList.isEmpty() || (permissionList.size() == 1 && !StringUtils.hasText(permissionList.get(0)))) {
                                    auth.antMatchers(p4p.getPath()).permitAll();
                                }
                                /* 限定配置的路径和权限 */
                                else {
                                    auth.antMatchers(p4p.getPath()).hasAnyAuthority(p4p.getPermission().toArray(new String[0]));
                                }
                            }
                    );
                    // 注册接口需要未验证访问
                    auth.antMatchers(securityConfigProperties.getRegisterApi()).anonymous();
                    // 登录接口需要未验证访问
                    auth.antMatchers(securityConfigProperties.getLoginApi()).anonymous();
                    // 其他所有接口允许匿名和验证访问
                    auth.anyRequest().permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // 允许跨域
        if (securityConfigProperties.isAllowCors()) {
            httpSecurity.cors();
        }
        return httpSecurity.build();
    }
}
