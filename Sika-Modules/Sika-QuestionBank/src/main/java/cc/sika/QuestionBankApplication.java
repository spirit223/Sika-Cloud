package cc.sika;

import cc.sika.common.swagger.annotation.EnableCustomSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 吴畅
 */
@SpringBootApplication
@EnableCustomSwagger
//@EnableAuthentication
public class QuestionBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionBankApplication.class, args);
    }
}