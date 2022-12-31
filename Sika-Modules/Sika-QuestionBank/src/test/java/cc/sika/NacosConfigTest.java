package cc.sika;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 15:50
 */
@SpringBootTest
public class NacosConfigTest {
    @Value("${pattern.dateformat}")
    private String datePattern;

    @Test
    void testGetConfig() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(datePattern)));
    }
}
