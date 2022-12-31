package cc.sika.service;

import cc.sika.api.bean.bo.QuestionBO;
import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteFailException;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:42
 */
public interface QuestionService {
    /**
     * 获取带有答案的题目
     *
     * @param questionId 题目id
     * @throws NoQuestionNumberException id不存在
     */
    QuestionWithAnswerBO getQuesByIdWithAnswer(int questionId) throws NoQuestionNumberException;

    /**
     * 获取题目信息
     *
     * @param questionId 题目id
     * @throws NoQuestionNumberException id不存在
     */
    QuestionBO getQueById(int questionId) throws NoQuestionNumberException;

    /**
     * 通过 Topic(分类)获取题目列表
     *
     * @param topic 分类
     * @return 题目信息列表
     */
    List<QuestionBO> getQuesByTopic(Topic topic);

    /**
     * 通过 Topic(分类) 获取带有答案的题目列表
     *
     * @param topic 分类
     * @return 带有答案的题目列表
     */
    List<QuestionWithAnswerBO> getQuesByTopicWithAnswer(Topic topic);

    /**
     * 获取所有题目信息的列表
     *
     * @return 所有题目信息(不包含答案)的列表
     */
    List<QuestionBO> getAllQues();

    /**
     * 获取所有带答案的题目信息列表
     *
     * @return 所有题目包含答案的BO列表, 详见 {@link QuestionWithAnswerBO}
     */
    List<QuestionWithAnswerBO> getAllQuesWithAnswer();

    /**
     * 添加题目信息
     *
     * @param question 题目PO 对象
     * @return 成功返回成功状态码枚举 HttpStatus.SUCCESS 详见 {@link HttpStatus}
     * @throws WriteFailException 资源耗尽, 死锁, 数据库崩溃
     */
    HttpStatus addQuestion(Question question) throws WriteFailException;

    /**
     * 添加题目信息, 如果题目包含答案, 会将答案同步存入数据库
     *
     * @param questionWithAnswer 题目信息BO对象, 详见 {@link QuestionWithAnswerBO}
     * @return 成功返回成功状态码枚举 HttpStatus.SUCCESS 详见 {@link HttpStatus}
     * @throws WriteFailException 资源耗尽, 死锁, 数据库崩溃
     */
    HttpStatus addQuestion(QuestionWithAnswerBO questionWithAnswer) throws WriteFailException;

    /**
     * 通过参数的question对象id更新题目信息
     *
     * @param question 要更新的题目PO对象
     * @return 更新成功返回成功状态码枚举 HttpStatus.SUCCESS 详见 {@link HttpStatus}
     * @throws WriteFailException 资源耗尽, 死锁, 数据库崩溃
     */
    HttpStatus updateQuestion(Question question) throws WriteFailException;

    /**
     * 删除题目信息
     *
     * @param questionId 要删除的题目id
     * @return 删除成功返回成功状态码枚举 HttpStatus.SUCCESS 详见 {@link HttpStatus}
     * @throws WriteFailException 资源耗尽, 死锁, 数据库崩溃
     */
    HttpStatus deleteQuestionById(int questionId) throws WriteFailException;

    /**
     * 批量添加题目
     *
     * @param questionList 包含题目PO的列表
     * @return 插入成功数量
     * @throws WriteFailException 资源耗尽, 死锁, 数据库崩溃
     */
    int addQuestionList(List<Question> questionList) throws WriteFailException;

    /**
     * 批量添加题目合答案
     *
     * @param questionBOList 包含 QuestionWithAnswerBO 的列表, 详见 {@link QuestionWithAnswerBO}
     * @return 插入成功的数量
     * @throws WriteFailException 插入异常
     */
    int addQuestionAndAnswerList(List<QuestionWithAnswerBO> questionBOList) throws WriteFailException;
}
