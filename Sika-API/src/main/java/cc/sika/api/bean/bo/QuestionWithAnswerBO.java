package cc.sika.api.bean.bo;

import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 业务层对象, 封装 Question 和 Answer
 * </p>
 * 带有 Question 相关属性, 将 answerId 替换成对应的 answer 字符串 和 图片(没有图片byte[]为null)
 *
 * @author 吴畅
 * @创建时间 2022/12/16 - 12:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithAnswerBO {
    private Integer questionId;
    private String questionType;
    private String questionContent;
    private byte[] questionImage;
    private String questionDescription;
    private String questionTopic;
    private Answer answer;

    public QuestionWithAnswerBO(Integer questionId, String questionType, String questionContent, byte[] questionImage, String questionDescription, String questionTopic, Integer answerId, String answerContent, byte[] answerImage, int answerQuestionId) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.questionImage = questionImage;
        this.questionDescription = questionDescription;
        this.questionTopic = questionTopic;
        this.answer = new Answer(answerId, answerContent, answerImage, answerQuestionId);
    }

    public Question getQuestion() {
        return new Question(questionId, questionType, questionContent, questionImage, questionDescription, answer.getAnswerId(), questionTopic);
    }

    public Answer getAnswer() {
        this.answer.setQId(this.questionId);
        return answer;
    }
}
