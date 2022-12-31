package cc.sika.mapper;

import cc.sika.api.bean.po.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴畅
 */
@Mapper
public interface AnswerMapper {
    /**
     * 通过答案id获取答案信息的po对象
     *
     * @param answerId int - 答案id
     * @return 携带 answer 信息的 po 对象, 如果不存在id对应的答案则返回空, 见 {@link Answer}
     */
    Answer getAnswerById(@Param("answerId") int answerId);

    /**
     * 通过答案id获取答案主体信息字符串
     *
     * @param answerId int - 答案id
     * @return 答案的主体信息字符串, 如果不存在id对应的答案则返回null
     */
    String getAnswerContentById(@Param("answerId") int answerId);

    /**
     * 通过题目的id获取出答案id
     *
     * @param questionId int - 题目id
     * @return int - 答案id
     */
    int getAnswerIdByQueId(@Param("questionId") int questionId);

    List<Answer> getAnswerByQueId(@Param("questionId") int questionId);

    /**
     * 通过题目的id获取出答案主体信息字符串
     *
     * @param questionId int - 题目id
     * @return String - 答案的主体信息, 不存在时为 null
     */
    String getContentByQueId(@Param("questionId") int questionId);

    /**
     * 通过答案id删除答案
     *
     * @param answerId int - 答案id
     * @return 删除成功返回1, 失败为0
     */
    int deleteAnswerById(@Param("answerId") int answerId);

    /**
     * 通过 {@link Answer} po 对象添加答案
     *
     * @param answer 存有答案信息的 po 对象
     * @return 添加成功为1, 失败为0
     */
    int addAnswer(Answer answer);

    /**
     * 批量添加答案
     *
     * @param answerList {@link Answer} po 对象列表
     * @return 添加答案的数量, 如果每一个答案都添加成功, 结果应与列表大小相同
     */
    int addAnswerList(List<Answer> answerList);

    /**
     * 更新答案, 通过 {@link Answer} po 对象的 id 属性匹配要修改的答案, 然后将其他非空字段覆盖
     *
     * @param answer 携带更新信息的po对象
     * @return 修改成功返回1, 失败返回0
     */
    int updateAnswer(Answer answer);

    /**
     * 更新答案和题目的id匹配
     *
     * @param questionId 题目id
     * @param answerId   答案id
     * @return 更新成功返回1, 失败返回0
     */
    int updateQueId(@Param("questionId") int questionId, @Param("answerId") int answerId);

    int deleteAnswerBatch(List<Integer> deleteIdList);

    int updateAnswerBatch(List<Answer> answerList);
}
