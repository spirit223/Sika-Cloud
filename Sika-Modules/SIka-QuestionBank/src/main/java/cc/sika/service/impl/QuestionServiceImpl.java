package cc.sika.service.impl;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import cc.sika.mapper.QuestionMapper;
import cc.sika.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:44
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Question getQuesById(int questionId) {
        return questionMapper.queryQuestionById(questionId);
    }

    @Override
    public List<Question> getQuesByTopic(Topic topic) {
        return questionMapper.queryQuestionByTopic(topic);
    }

    @Override
    public List<Question> getAllQues() {
        return questionMapper.queryAllQuestion();
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
}
