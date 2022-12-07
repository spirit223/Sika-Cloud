package cc.sika;

import cc.sika.api.domain.Question;
import cc.sika.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 15:41
 */
@SpringBootTest
public class QuestionBankTest {
    @Resource(name = "questionMapper")
    private QuestionMapper questionMapper;

    @Test
    void testInsert() {
        Question question = new Question('1', "给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标",
                null, "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现",
                null, "算法题");
        int i = questionMapper.addQuestion(question);
        System.out.println("插入题目数量为:" + i);
    }

    @Test
    void testUpdate() {
        Question question = new Question(2, '1', "给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false",
                null, "回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数",
                null, "算法题");
        int i = questionMapper.updateQuestion(question);
        System.out.println("修改题目数量为:" + i);
    }

    @Test
    void testDelete() {
        int i = questionMapper.deleteQuestion(2);
        System.out.println("删除条数为: " + i);
    }
}
