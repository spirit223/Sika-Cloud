package cc.sika;

import cc.sika.api.bean.po.Answer;
import cc.sika.mapper.AnswerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/16 - 17:36
 */
@SpringBootTest
public class AnswerMapperTest {

    @Resource
    private AnswerMapper answerMapper;


    List<Answer> answerList = new ArrayList<>(Arrays.asList(
            new Answer("测试批量插入answer1", null),
            new Answer("测试批量插入answer2", null),
            new Answer("测试批量插入answer3", null),
            new Answer("测试批量插入answer4", null)
    ));

    @Test
    void testAddAnswerList() {
        int i = answerMapper.addAnswerList(answerList);
        System.out.println("插入数量: " + i);
    }

    @Test
    void testGetAnswerContent() {
        String s = answerMapper.getAnswerContentById(10);
        if (s == null) {
            System.out.println("查询结果为null");
        }
        System.out.println(s);
    }

    @Test
    void testDeleteBatch() {
        int i = answerMapper.deleteAnswerBatch(Arrays.asList(8, 9));
    }
}
