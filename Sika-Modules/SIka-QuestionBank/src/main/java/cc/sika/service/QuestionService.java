package cc.sika.service;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import cc.sika.api.vo.QuestionDTO;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteQuestionFailException;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:42
 */
public interface QuestionService {
    QuestionDTO getQuesById(int questionId) throws NoQuestionNumberException;

    List<QuestionDTO> getQuesByTopic(Topic topic);

    List<QuestionDTO> getAllQues();

    HttpStatus addQuestion(Question question) throws WriteQuestionFailException;

    HttpStatus updateQuestion(Question question) throws WriteQuestionFailException;

    HttpStatus deleteQuestionById(int questionId) throws WriteQuestionFailException;
}
