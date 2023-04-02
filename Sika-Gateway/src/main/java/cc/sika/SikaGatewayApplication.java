package cc.sika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 吴畅
 * @创建时间 2023/2/3 - 21:53
 */
@SpringBootApplication
@EnableFeignClients
public class SikaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SikaGatewayApplication.class, args);
    }
}
