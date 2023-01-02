package cc.sika;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Question;
import cc.sika.mapper.QuestionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 15:41
 */
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionBankTest {
    @Resource(name = "questionMapper")
    private QuestionMapper questionMapper;

    @Resource
    private ObjectMapper jsonMapper;

    List<Question> questionList = new ArrayList<>(Arrays.asList(new Question("1", "给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表", null, "可以假设除了数字 0 之外，这两个数都不会以 0 开头", 1, "算法题"),
            new Question("1", "给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度", null, "", 2, "字符串"),
            new Question("2", "给你一个链表数组，每个链表都已经按升序排列", null, "请你将所有链表合并到一个升序链表中，返回合并后的链表", 3, "链表")));

    @Test
    void testFuzzyQuery() {
        List<QuestionWithAnswerBO> fuzzyList = questionMapper.getQuestionAndAnswerByFuzzy("给你");
        fuzzyList.forEach(System.out::println);
    }

    @Test
    void name() {
        System.out.println(Arrays.toString(questionList.toArray()));
    }

    @Test
    void testController(@Autowired MockMvc mockMvc) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("http://localhost:8104/question/addBatch");

        builder.contentType("application/json");
        builder.content(jsonMapper.writeValueAsString(questionList));
//        builder.param("questionList", Arrays.toString(questionList.toArray()));
        ResultActions perform = mockMvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
    }

    @Test
    void testBatchAdd() {
        int addCount = questionMapper.addQuestionList(questionList);
        System.out.println(addCount);
    }

    @Test
    void testGetQAByTopic() {
        List<QuestionWithAnswerBO> boList = questionMapper.queryQuesAndAnswerByTopic(new Topic("算法题"));
        boList.forEach(System.out::println);
    }

    @Test
    void testGetQAByTopicNon() {
        List<QuestionWithAnswerBO> boList = questionMapper.queryQuesAndAnswerByTopic(new Topic(""));
        System.out.println(boList);
    }

    @Test
    void testGetAllTopic() {
        List<Topic> allTopic = questionMapper.getAllTopic();
        allTopic.forEach(System.out::println);
    }

    @Test
    void testQueryQuesAndAnswer() {
        QuestionWithAnswerBO questionWithAnswerBO = questionMapper.queryQuesAndAnswer(1);
        System.out.println(questionWithAnswerBO);
    }

    @Test
    void testInsert() {
        Question question = new Question("1", "给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标",
                null, "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现",
                null, "算法题");
        int i = questionMapper.addQuestion(question);
        System.out.println("插入题目数量为:" + i);
    }

    @Test
    void testUpdate() {
        Question question = new Question(2, "1", "给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false",
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

    @Test
    void testGetAllQA() {
        List<QuestionWithAnswerBO> questionWithAnswerBOList = questionMapper.queryAllQuestionAndAnswer();
        if (questionWithAnswerBOList == null || questionWithAnswerBOList.isEmpty()) {
            System.out.println("空列表");
            return;
        }
        questionWithAnswerBOList.forEach(System.out::println);
    }

    @Test
    void testGetTopicQA() {
        List<QuestionWithAnswerBO> questionWithAnswerBOList = questionMapper.queryQuesAndAnswerByTopic(new Topic("Java基础"));
        questionWithAnswerBOList.forEach(System.out::println);
    }

}
