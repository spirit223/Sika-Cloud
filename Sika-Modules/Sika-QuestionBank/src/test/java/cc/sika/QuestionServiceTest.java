package cc.sika;

import cc.sika.api.bean.bo.QuestionBO;
import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.Topic;
import cc.sika.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/16 - 16:12
 */
@SpringBootTest
public class QuestionServiceTest {
    @Resource
    private QuestionService questionService;

    @Test
    void testGetTopicByNull() {
        List<QuestionWithAnswerBO> quesByTopicWithAnswer = questionService.getQuesByTopicWithAnswer(new Topic(""));
        System.out.println(quesByTopicWithAnswer);
    }

    @Test
    void testGetTopicByValue() {
        List<QuestionWithAnswerBO> quesByTopicWithAnswer = questionService.getQuesByTopicWithAnswer(new Topic("算法题"));
        System.out.println(quesByTopicWithAnswer);
    }

    @Test
    void testGetQuesByTopic() {
        List<QuestionBO> ques = questionService.getQuesByTopic(new Topic("算法题"));
        printList(ques);
    }

    @SuppressWarnings("all")
    private void printList(List list) {
        list.forEach(System.out::println);
    }
}
