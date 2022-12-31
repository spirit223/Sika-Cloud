package cc.sika;

import cc.sika.api.bean.po.Answer;
import cc.sika.mapper.AnswerMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 吴畅
 * @创建时间 2022/12/31 - 20:00
 */
@SpringBootTest
@Slf4j
public class MyBatisGenerateKeyTest {
    @Autowired
    private AnswerMapper answerMapper;

    @Test
    void testUseGenerateKey() {
        Answer answer = new Answer("测试生成主键的答案", null, -1);
        log.warn("插入前answer的主键为=> {} <=", answer.getAnswerId());
        answerMapper.addAnswer(answer);
        log.warn("插入后answer的主键为=> {} <=", answer.getAnswerId());
    }
}
