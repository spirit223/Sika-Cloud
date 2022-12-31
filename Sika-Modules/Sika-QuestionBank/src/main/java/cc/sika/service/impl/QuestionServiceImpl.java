//package cc.sika.service.impl;
//
//import cc.sika.api.common.HttpStatus;
//import cc.sika.api.bean.po.Question;
//import cc.sika.api.bean.dto.Topic;
//import cc.sika.api.bean.dto.QuestionDTO;
//import cc.sika.exception.NoQuestionNumberException;
//import cc.sika.exception.WriteQuestionFailException;
//import cc.sika.mapper.AnswerMapper;
//import cc.sika.mapper.QuestionMapper;
//import cc.sika.service.QuestionService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author 吴畅
// * @创建时间 2022/12/7 - 16:44
// */
//@Service("questionService")
//public class QuestionServiceImpl implements QuestionService {
//    @Resource
//    private QuestionMapper questionMapper;
//    @Resource
//    private AnswerMapper answerMapper;
//
//    /**
//     * 通过 id 获取 QuestionDTO 对象, 对象中携带 answer 属性
//     *
//     * @return 带有answer属性的QuestionDTO
//     */
//    @Override
//    public QuestionDTO getQuesById(int questionId) throws NoQuestionNumberException {
//        Question question = questionMapper.queryQuestionById(questionId);
//        if (question == null) {
//            throw new NoQuestionNumberException("There is no question with number: " + questionId);
//        }
//        String answerContent = answerMapper.queryContentByQueId(question.getQuestionId());
//        if (answerContent == null) {
//            answerContent = "";
//        }
//        return new QuestionDTO(question, answerContent);
//    }
//
//    /**
//     * 通过题目的 topic 获取一个带有answer属性的QuestionDTO 列表
//     *
//     * @return 带有answer属性的QuestionDTO-List<QuestionDTO>
//     */
//    @Override
//    public List<QuestionDTO> getQuesByTopic(Topic topic) {
//        List<Question> questions = questionMapper.queryQuestionByTopic(topic);
//        return toDTO(questions);
//    }
//
//    /**
//     * 获取所有题目
//     *
//     * @return 带有answer属性的QuestionDTO-List
//     */
//    @Override
//    public List<QuestionDTO> getAllQues() {
//        List<Question> questions = questionMapper.queryAllQuestion();
//        return toDTO(questions);
//    }
//
//    /*---------------------------------------------------------------------*/
//
//    @Override
//    @Transactional(rollbackFor = {WriteQuestionFailException.class, RuntimeException.class})
//    public HttpStatus addQuestion(Question question) throws WriteQuestionFailException {
//        int addResult = questionMapper.addQuestion(question);
//        if (addResult > 0) {
//            return HttpStatus.SUCCESS;
//        } else {
//            throw new WriteQuestionFailException("添加题目失败!");
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = {WriteQuestionFailException.class, RuntimeException.class})
//    public HttpStatus updateQuestion(Question question) throws WriteQuestionFailException {
//        int updateResult = questionMapper.updateQuestion(question);
//        if (updateResult > 0) {
//            return HttpStatus.SUCCESS;
//        } else {
//            throw new WriteQuestionFailException("修改题目失败!");
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = {WriteQuestionFailException.class, RuntimeException.class})
//    public HttpStatus deleteQuestionById(int questionId) throws WriteQuestionFailException {
//        int deleteResult = questionMapper.deleteQuestion(questionId);
//        if (deleteResult > 0) {
//            return HttpStatus.SUCCESS;
//        } else {
//            throw new WriteQuestionFailException("删除题目失败!");
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = {WriteQuestionFailException.class, RuntimeException.class})
//    public int addQuestionList(List<Question> questionList) throws WriteQuestionFailException {
//        int addCount = questionMapper.addQuestionList(questionList);
//        if (addCount == 0) {
//            throw new WriteQuestionFailException("插入数据条数为0, 插入的列表大小为: " + questionList.size());
//        }
//        return addCount;
//    }
//
//    /**
//     * 将 Question 列表中每一个 Question 转换成 DTO 对象
//     *
//     * @param questionList 没有answer属性的Question的List<Question>
//     * @return 带有answer属性的QuestionDTO的List<QuestionDTO>
//     */
//    private List<QuestionDTO> toDTO(List<Question> questionList) {
//        return questionList.stream().map(question -> {
//            String answer = answerMapper.queryContentByQueId(question.getQuestionId());
//            return new QuestionDTO(question, answer);
//        }).collect(Collectors.toList());
//    }
//}
