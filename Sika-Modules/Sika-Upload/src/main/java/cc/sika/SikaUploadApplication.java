package cc.sika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 16:49
 */
@SpringBootApplication
@EnableFeignClients
public class SikaUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(SikaUploadApplication.class, args);
    }
}
