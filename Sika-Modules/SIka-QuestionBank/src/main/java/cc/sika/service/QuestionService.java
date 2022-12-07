package cc.sika.service;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:42
 */
public interface QuestionService {
    Question getQuesById(int questionId);

    List<Question> getQuesByTopic(Topic topic);

    List<Question> getAllQues();

    HttpStatus addQuestion(Question question);

    HttpStatus updateQuestion(Question question);

    HttpStatus deleteQuestionById(int questionId);
}
