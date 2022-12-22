package cc.sika.common.security;

import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 12:20
 */
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ObjectMapper jsonMapper;

    @Test
    void testSet() throws JsonProcessingException {
        User user = new User();
        user.setPassword("wjc52292");
        redisUtils.setObject("key1", user);
        Object value1 = redisUtils.getObject("key1");
        System.out.println(value1);

        User user1 = redisUtils.getObject("key1", User.class);
        System.out.println(user1);
    }

    @Test
    void name() throws JsonProcessingException {
        User user = new User();
        user.setPassword("wjc52292");

        Object key1 = redisUtils.getObject("key1");
        System.out.println(key1);
        System.out.println();

        String s1 = jsonMapper.writeValueAsString(key1);
        System.out.println(s1);
        System.out.println();

        String s = jsonMapper.writeValueAsString(user);
        System.out.println(s);
        System.out.println();
    }
}
