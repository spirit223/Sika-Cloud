package cc.sika.service.impl;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Answer;
import cc.sika.api.domain.Question;
import cc.sika.mapper.AnswerMapper;
import cc.sika.mapper.QuestionMapper;
import cc.sika.service.QueAnsService;
import cc.sika.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 20:59
 */
@Service
public class QueAnsServiceImpl implements QueAnsService {
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private AnswerMapper answerMapper;

    /**
     * 将 问题 和 答案 同时设置
     *
     * @param question Question
     * @param answer   Answer
     * @return 参数对象存在null时返回BAD_REQUEST, 添加成功返回 SUCCESS, 否则返回 ERROR
     */
    @Override
    public HttpStatus addQueAndAns(Question question, Answer answer) {
        if (BeanUtils.isNull(question, answer)) {
            return HttpStatus.BAD_REQUEST;
        }

        if (question.getAnswerId() == null) {
            question.setAnswerId(answer.getAnswerId());
        }
        int ques = questionMapper.addQuestion(question);
        answer.setQuestionId(question.getQuestionId());
        int answ = answerMapper.addAnswer(answer);
        if (ques == 1 && answ == 1) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    /**
     * 将问题和答案进行绑定
     *
     * @param answerId   int
     * @param QuestionId int
     * @return 更新后如果两个表中的answerId相等返回 true
     */
    @Override
    public boolean binding(int answerId, int QuestionId) {
        questionMapper.updateQueAnswer(QuestionId, answerId);
        answerMapper.updateQueId(QuestionId, answerId);
        return questionMapper.queryQuestionById(QuestionId).getAnswerId() == answerId;
    }
}