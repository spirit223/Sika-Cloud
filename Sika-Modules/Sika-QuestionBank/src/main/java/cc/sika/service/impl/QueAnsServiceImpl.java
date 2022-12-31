package cc.sika.service.impl;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import cc.sika.mapper.AnswerMapper;
import cc.sika.mapper.QuestionMapper;
import cc.sika.service.QueAnsService;
import cc.sika.utils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public HttpStatus addQA(QuestionWithAnswerBO qa) {
        Question question = new Question(qa.getQuestionId(), qa.getQuestionType(), qa.getQuestionContent(),
                qa.getQuestionImage(), qa.getQuestionDescription(), qa.getAnswer().getAnswerId(), qa.getQuestionTopic());
        Answer answer = qa.getAnswer();
        answer.setQuestionId(qa.getQuestionId());
        return addQA(question, answer);
    }

    /**
     * 将 问题 和 答案 同时设置
     *
     * @param question Question
     * @param answer   Answer
     * @return 参数对象存在null时返回BAD_REQUEST, 添加成功返回 SUCCESS, 否则返回 ERROR
     */
    @Override
    @Transactional
    public HttpStatus addQA(Question question, Answer answer) {
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
    @Transactional
    public boolean binding(int answerId, int QuestionId) {
        questionMapper.updateQueAnswer(QuestionId, answerId);
        answerMapper.updateQueId(QuestionId, answerId);
        return questionMapper.queryQuestionById(QuestionId).getAnswerId() == answerId;
    }

    @Override
    @Transactional
    public HttpStatus addQABatch(List<Question> qList, List<Answer> aList) {
        /* 题目和答案数量不匹配 */
        if (!(qList.size() == aList.size())) {
            return HttpStatus.BAD_REQUEST;
        }
        for (int i = 0; i < qList.size(); i++) {
            Question question = qList.get(i);
            Answer answer = aList.get(i);
            logicBind(question, answer);
            // 先插入答案, 利用MyBatis生成的主键匹配
            answerMapper.addAnswer(answer);
            question.setAnswerId(answer.getAnswerId());
            questionMapper.addQuestion(question);
        }

        return HttpStatus.SUCCESS;
    }

    @Override
    @Transactional
    public HttpStatus addQABatch(List<QuestionWithAnswerBO> qaList) {
        List<Question> qList = qaList.stream().map(bo -> new Question(bo.getQuestionId(), bo.getQuestionType(),
                bo.getQuestionContent(), bo.getQuestionImage(),
                bo.getQuestionDescription(), bo.getAnswer().getAnswerId(), bo.getQuestionTopic())).collect(Collectors.toList());
        List<Answer> aList = qaList.stream().map(bo -> new Answer(bo.getAnswer().getAnswerId(),
                bo.getAnswer().getAnswerContent(),
                bo.getAnswer().getAnswerImage(),
                bo.getQuestionId())).collect(Collectors.toList());
        return addQABatch(qList, aList);
    }

    private void logicBind(Question question, Answer answer) {
        question.setAnswerId(answer.getAnswerId());
        answer.setQuestionId(question.getQuestionId());
    }

}
