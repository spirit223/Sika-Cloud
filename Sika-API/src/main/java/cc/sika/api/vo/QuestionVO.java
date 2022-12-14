package cc.sika.api.vo;

import cc.sika.api.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吴畅
 * @创建时间: 2022/12/7 - 20:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVO {
    private Integer questionId;
    private char questionType;
    private String questionContent;
    private byte[] questionImage;
    private String questionDescription;
    private String answer;
    private String questionTopic;

    public QuestionVO(Question question, String answer) {
        this.questionId = question.getQuestionId();
        this.questionType = question.getQuestionType();
        this.questionContent = question.getQuestionContent();
        this.questionImage = question.getQuestionImage();
        this.questionDescription = question.getQuestionDescription();
        this.questionTopic = question.getQuestionTopic();
        this.answer = answer;
    }
}
