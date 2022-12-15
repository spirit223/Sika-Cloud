package cc.sika.service.impl;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import cc.sika.api.vo.QuestionDTO;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteQuestionFailException;
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
     * @return 带有answer属性的QuestionVO
     */
    @Override
    public QuestionDTO getQuesById(int questionId) throws NoQuestionNumberException {
        Question question = questionMapper.queryQuestionById(questionId);
        if (question == null) {
            throw new NoQuestionNumberException("There is no question with number: " + questionId);
        }
        String answerContent = answerMapper.queryContentByQueId(question.getQuestionId());
        return new QuestionDTO(question, answerContent);
    }

    /**
     * 通过题目的 topic 获取一个带有answer属性的QuestionVO 列表
     *
     * @return 带有answer属性的QuestionVO-List<QuestionVO>
     */
    @Override
    public List<QuestionDTO> getQuesByTopic(Topic topic) {
        List<Question> questions = questionMapper.queryQuestionByTopic(topic);
        return toVO(questions);
    }

    /**
     * 获取所有题目
     *
     * @return 带有answer属性的QuestionVO-List
     */
    @Override
    public List<QuestionDTO> getAllQues() {
        List<Question> questions = questionMapper.queryAllQuestion();
        return toVO(questions);
    }

    @Override
    public HttpStatus addQuestion(Question question) throws WriteQuestionFailException {
        int addResult = questionMapper.addQuestion(question);
        if (addResult > 0) {
            return HttpStatus.SUCCESS;
        } else {
            throw new WriteQuestionFailException("添加题目失败!");
        }
    }

    @Override
    public HttpStatus updateQuestion(Question question) throws WriteQuestionFailException {
        int updateResult = questionMapper.updateQuestion(question);
        if (updateResult > 0) {
            return HttpStatus.SUCCESS;
        } else {
            throw new WriteQuestionFailException("修改题目失败!");
        }
    }

    @Override
    public HttpStatus deleteQuestionById(int questionId) throws WriteQuestionFailException {
        int deleteResult = questionMapper.deleteQuestion(questionId);
        if (deleteResult > 0) {
            return HttpStatus.SUCCESS;
        } else {
            throw new WriteQuestionFailException("删除题目失败!");
        }
    }

    /**
     * 将 Question 列表中每一个 Question 转换成 VO 对象
     *
     * @param questionList 没有answer属性的Question的List<Question>
     * @return 带有answer属性的QuestionVO的List<QuestionVO>
     */
    private List<QuestionDTO> toVO(List<Question> questionList) {
        return questionList.stream().map(question -> {
            String answer = answerMapper.queryContentByQueId(question.getQuestionId());
            return new QuestionDTO(question, answer);
        }).collect(Collectors.toList());
    }
}
