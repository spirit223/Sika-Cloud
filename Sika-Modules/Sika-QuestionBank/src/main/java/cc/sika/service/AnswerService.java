package cc.sika.service;

import cc.sika.api.bean.po.Answer;
import cc.sika.api.common.HttpStatus;
import cc.sika.exception.NoAnswerException;
import cc.sika.exception.WriteFailException;

import java.util.List;

/**
 * 操作答案
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 20:52
 */
public interface AnswerService {

    /**
     * 获取答案信息
     *
     * @param id 答案id
     * @return 装有答案信息的 po 对象
     */
    Answer getAnswer(int id) throws NoAnswerException;

    List<Answer> getAnswerByQue(int questionId) throws NoAnswerException;

    HttpStatus deleteAnswer(int answerId) throws WriteFailException;

    int deleteAnswerBatch(List<Integer> deleteIdList);

    /**
     * 从 {@link Answer} po 对象获取数据并进行持久化存储
     *
     * @param answer 携带答案数据的 po 对象
     * @return 添加成功返回1, 失败返回0
     * @throws WriteFailException 资源耗尽, 数据库上锁时, 添加失败并抛出
     */
    HttpStatus addAnswer(Answer answer) throws WriteFailException;

    int addAnswerBatch(List<Answer> answerList);

    HttpStatus updateAnswer(Answer answer) throws WriteFailException;

    int updateAnswerBatch(List<Answer> answerList);

}
