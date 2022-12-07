package cc.sika.mapper;


import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴畅
 */
@Mapper
public interface QuestionMapper {
    Question queryQuestionById(@Param("questionId") int questionId);

    List<Question> queryQuestionByTopic(Topic topic);

    List<Question> queryAllQuestion();

    int addQuestion(Question question);

    int updateQuestion(Question question);

    int deleteQuestion(@Param("questionId") int questionId);

}
