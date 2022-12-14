package cc.sika.service.impl;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import cc.sika.api.vo.QuestionVO;
import cc.sika.mapper.AnswerMapper;
import cc.sika.mapper.QuestionMapper;
import cc.sika.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:44
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private AnswerMapper answerMapper;

    /**
     * 通过 id 获取 QuestionVO 对象, 对象中携带 answer 属性
     *
     * @param questionId
     * @return 带有answer属性的QuestionVO
     */
    @Override
    public QuestionVO getQuesById(int questionId) {
        Question question = questionMapper.queryQuestionById(questionId);
        String answerContent = answerMapper.queryContentByQueId(question.getQuestionId());
        return new QuestionVO(question, answerContent);
    }

    /**
     * 通过题目的 topic 获取一个带有answer属性的QuestionVO 列表
     *
     * @param topic
     * @return 带有answer属性的QuestionVO-List<QuestionVO>
     */
    @Override
    public List<QuestionVO> getQuesByTopic(Topic topic) {
        List<Question> questions = questionMapper.queryQuestionByTopic(topic);
        return toVO(questions);
    }

    /**
     * 获取所有题目
     *
     * @return 带有answer属性的QuestionVO-List
     */
    @Override
    public List<QuestionVO> getAllQues() {
        List<Question> questions = questionMapper.queryAllQuestion();
        return toVO(questions);
    }

    @Override
    public HttpStatus addQuestion(Question question) {
        int addResult = questionMapper.addQuestion(question);
        if (addResult > 0) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    @Override
    public HttpStatus updateQuestion(Question question) {
        int updateResult = questionMapper.updateQuestion(question);
        if (updateResult > 0) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    @Override
    public HttpStatus deleteQuestionById(int questionId) {
        int deleteResult = questionMapper.deleteQuestion(questionId);
        if (deleteResult > 0) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    /**
     * 将 Question 列表中每一个 Question 转换成 VO 对象
     *
     * @param questionList 没有answer属性的Question的List<Question>
     * @return 带有answer属性的QuestionVO的List<QuestionVO>
     */
    private List<QuestionVO> toVO(List<Question> questionList) {
        return questionList.stream().map(question -> {
            String answer = answerMapper.queryContentByQueId(question.getQuestionId());
            return new QuestionVO(question, answer);
        }).collect(Collectors.toList());
    }
}
