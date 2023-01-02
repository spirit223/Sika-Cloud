package cc.sika.mapper;


import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定义 question 表原子操作的 dao 层接口
 *
 * @author 吴畅
 */
@Mapper
public interface QuestionMapper {

    /**
     * 获取所有的 topic
     *
     * @return Topic 列表
     */
    List<Topic> getAllTopic();

    /**
     * 通过 题目id 获取题目对象, po.Question
     *
     * @param questionId 题目id
     * @return 对象中只包含答案id, 没有答案主体
     */
    Question queryQuestionById(@Param("questionId") int questionId);

    List<Question> queryQuestionByContent(@Param("questionContent") String content);

    /**
     * 通过题目id获取题目对象, 获取出来的题目对象是BO对象,会携带答案信息
     *
     * @param questionId 题目id
     * @return QuestionWithAnswerBO 携带答案信息的BO对象
     */
    QuestionWithAnswerBO queryQuesAndAnswer(@Param("questionId") int questionId);

    /**
     * 通过分类查询题目信息, 获取出同个分类的所有题目信息封装成列表
     *
     * @param topic 分类名称, 是对字符串的封装
     * @return 列表中为题目信息PO, 不携带答案
     */
    List<Question> queryQuestionByTopic(Topic topic);

    /**
     * 通过分类查询题目信息和答案, 获取出同个分类的所有题目信息携带答案封装成列表
     *
     * @param topic 分类名称, 是对字符串的封装
     * @return 列表中为题目信息BO, 携带答案信息
     */
    List<QuestionWithAnswerBO> queryQuesAndAnswerByTopic(Topic topic);

    /**
     * 查询所有题目信息, 不携带答案
     */
    List<Question> queryAllQuestion();

    /**
     * 查询所有题目同时匹配答案
     */
    List<QuestionWithAnswerBO> queryAllQuestionAndAnswer();

    /**
     * 添加单个题目
     *
     * @param question 要添加的题目信息po对象
     * @return 添加成功返回 1
     */
    int addQuestion(Question question);

    /**
     * 将列表中的所有题目添加到数据库
     *
     * @param questionList 题目信息列表
     * @return 添加成功的数量
     */
    int addQuestionList(List<Question> questionList);

    /**
     * 更新题目信息
     *
     * @param question 要修改的题目信息po对象
     * @return 修改成功返回1
     */
    int updateQuestion(Question question);

    int updateQueAnswer(@Param("questionId") int questionId, @Param("answerId") int answerId);

    /**
     * 删除题目
     *
     * @param questionId 删除题目的id
     * @return 删除成功返回1
     */
    int deleteQuestion(@Param("questionId") int questionId);


    /**
     * 通过题目内容模糊查询题目信息, 返回多个题目包含答案的列表
     *
     * @param fuzzy 模糊查询的关键字
     * @return 多个题目包含答案的列表
     */
    List<QuestionWithAnswerBO> getQuestionAndAnswerByFuzzy(@Param("fuzzy") String fuzzy);
}
