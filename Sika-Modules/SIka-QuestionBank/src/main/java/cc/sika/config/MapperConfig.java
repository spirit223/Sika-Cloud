package cc.sika.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:07
 */
@Configuration
@MapperScan(basePackages = "cc.sika.mapper")
public class MapperConfig {
}
