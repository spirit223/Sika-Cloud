package cc.sika.mapper;

import cc.sika.api.domain.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 吴畅
 */
@Mapper
public interface AnswerMapper {
    Answer queryAnswerById(@Param("answerId") int answerId);

    String queryAnswerContentById(@Param("answerId") int answerId);

    int queryAnswerIdByQueId(@Param("questionId") int questionId);

    String queryContentByQueId(@Param("questionId") int questionId);


    int deleteAnswerById(@Param("answerId") int answerId);

    int addAnswer(Answer answer);

    int updateAnswer(Answer answer);

    int updateQueId(@Param("questionId") int questionId, @Param("answerId") int answerId);
}
