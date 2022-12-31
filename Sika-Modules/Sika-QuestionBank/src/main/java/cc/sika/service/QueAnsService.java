package cc.sika.service;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;

import java.util.List;

/**
 * 同时操作问题和答案
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 20:50
 */
public interface QueAnsService {
    /**
     * 通过题目和答案对象同时添加进数据库
     */
    HttpStatus addQA(Question question, Answer answer);

    /**
     * 通过QA的业务对象添加题目和答案
     *
     * @param qa 携带答案PO对象的题目信息BO, 见{@link QuestionWithAnswerBO}
     */
    HttpStatus addQA(QuestionWithAnswerBO qa);

    /**
     * 将题目和答案进行关联
     *
     * @param answerId   答案id
     * @param QuestionId 题目id
     * @return 绑定成功返回true
     */
    boolean binding(int answerId, int QuestionId);

    /**
     * 通过题目和答案列表批量添加数据
     */
    HttpStatus addQABatch(List<Question> qList, List<Answer> aList);

    /**
     * 通过QA的业务对象列表添加题目和答案
     *
     * @param qaList qa 携带答案PO对象的题目信息BO, 见{@link QuestionWithAnswerBO}
     */
    HttpStatus addQABatch(List<QuestionWithAnswerBO> qaList);
}
