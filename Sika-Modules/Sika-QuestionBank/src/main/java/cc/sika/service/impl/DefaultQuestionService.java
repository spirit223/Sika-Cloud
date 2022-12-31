package cc.sika.service.impl;

import cc.sika.api.bean.bo.QuestionBO;
import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteFailException;
import cc.sika.mapper.AnswerMapper;
import cc.sika.mapper.QuestionMapper;
import cc.sika.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 吴畅
 */
@Service
public class DefaultQuestionService implements QuestionService {
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private AnswerMapper answerMapper;

    @Override
    public QuestionWithAnswerBO getQuesByIdWithAnswer(int questionId) throws NoQuestionNumberException {
        QuestionWithAnswerBO questionWithAnswerBO = questionMapper.queryQuesAndAnswer(questionId);
        if (Objects.isNull(questionWithAnswerBO)) {
            throw new NoQuestionNumberException(questionId);
        }
        return questionWithAnswerBO;
    }

    @Override
    public QuestionBO getQueById(int questionId) throws NoQuestionNumberException {
        Question questionPO = questionMapper.queryQuestionById(questionId);
        if (Objects.isNull(questionPO)) {
            throw new NoQuestionNumberException(questionId);
        }
        return new QuestionBO(questionPO);
    }

    @Override
    public List<QuestionBO> getQuesByTopic(Topic topic) {
        List<Question> questions = questionMapper.queryQuestionByTopic(topic);
        if (questions.isEmpty()) {
            return new ArrayList<>();
        }
        return questions.stream().map(QuestionBO::new).collect(Collectors.toList());
    }

    /**
     * 通过 Topic(分类) 获取带有答案的题目列表
     *
     * @param topic 分类
     * @return 带有答案的题目列表, 分类为空时返回未分类的的 QuestionWithAnswerBO 列表
     */
    @Override
    public List<QuestionWithAnswerBO> getQuesByTopicWithAnswer(Topic topic) {
        /* 分类为空值或者空串 */
        if (!StringUtils.hasText(topic.getTopicName())) {
            return questionMapper.queryQuesAndAnswerByTopic(new Topic(""));
        }

        return questionMapper.queryQuesAndAnswerByTopic(topic);
    }

    @Override
    public List<QuestionBO> getAllQues() {
        List<Question> questions = questionMapper.queryAllQuestion();
        return questions.stream().map(QuestionBO::new).collect(Collectors.toList());
    }

    @Override
    public List<QuestionWithAnswerBO> getAllQuesWithAnswer() {
        return questionMapper.queryAllQuestionAndAnswer();
    }


    /*-----------------------------------------------------------------------*/
    @Override
    public HttpStatus addQuestion(Question question) throws WriteFailException {
        int addResult = questionMapper.addQuestion(question);
        checkResult(addResult, null);
        return HttpStatus.SUCCESS;
    }

    @Override
    public HttpStatus addQuestion(QuestionWithAnswerBO questionWithAnswer) throws WriteFailException {
        Answer answer = questionWithAnswer.getAnswer();
        int questionResult = questionMapper.addQuestion(new Question(questionWithAnswer));
        checkResult(questionResult, "题目写入失败");
        /* 包含有答案, 同步写入数据库 */
        if (!Objects.isNull(answer)) {
            int answerResult = answerMapper.addAnswer(answer);
            checkResult(answerResult, "答案写入失败!");
        }
        return HttpStatus.SUCCESS;
    }

    @Override
    public HttpStatus updateQuestion(Question question) throws WriteFailException {
        int updateResult = questionMapper.updateQuestion(question);
        checkResult(updateResult, "更新题目失败");
        return HttpStatus.SUCCESS;
    }

    @Override
    public HttpStatus deleteQuestionById(int questionId) throws WriteFailException {
        int deleteResult = questionMapper.deleteQuestion(questionId);
        checkResult(deleteResult, "删除");
        return HttpStatus.SUCCESS;
    }

    @Override
    public int addQuestionList(List<Question> questionList) throws WriteFailException {
        int batchResult = questionMapper.addQuestionList(questionList);
        if (batchResult <= 0 || batchResult != questionList.size()) {
            throw new WriteFailException("批量插入失败");
        }
        return batchResult;
    }

    @Override
    public int addQuestionAndAnswerList(List<QuestionWithAnswerBO> questionBOList) throws WriteFailException {
        List<Answer> answerList = questionBOList.stream().map(QuestionWithAnswerBO::getAnswer).collect(Collectors.toList());
        int answerAddCount = answerMapper.addAnswerList(answerList);
        int questionAddCount = questionMapper.addQuestionList(questionBOList.stream().map(Question::new).collect(Collectors.toList()));
        checkResult(answerAddCount, "答案同步插入异常");
        checkResult(questionAddCount, "题目插入异常");
        return questionAddCount;
    }


    private void checkResult(int count, String failMessage) throws WriteFailException {
        if (count == 0) {
            if (!StringUtils.hasText(failMessage)) {
                throw new WriteFailException();
            } else {
                throw new WriteFailException(failMessage);
            }
        }
    }
}
