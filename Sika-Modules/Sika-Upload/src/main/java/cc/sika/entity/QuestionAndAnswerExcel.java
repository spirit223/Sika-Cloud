package cc.sika.entity;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 22:53
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class QuestionAndAnswerExcel {
    @ExcelProperty("question_id")
    private Integer questionId;
    @ExcelProperty("question_type")
    private String questionType;
    @ExcelProperty("question_content")
    private String questionContent;
    @ExcelProperty("question_image")
    private byte[] questionImage;
    @ExcelProperty("question_description")
    private String questionDescription;
    @ExcelProperty("question_topic")
    private String questionTopic;
    @ExcelProperty("answer_id")
    private Integer answerId;
    @ExcelProperty("answer_content")
    private String answerContent;
    @ExcelProperty("answer_image")
    private byte[] answerImage;

    public QuestionAndAnswerExcel(QuestionWithAnswerBO qa) {
        questionId = qa.getQuestionId();
        questionType = qa.getQuestionType();
        questionContent = qa.getQuestionContent();
        questionImage = qa.getQuestionImage();
        questionDescription = qa.getQuestionDescription();
        questionTopic = qa.getQuestionTopic();
        answerId = qa.getAnswer().getAnswerId();
        answerContent = qa.getAnswer().getAnswerContent();
        answerImage = qa.getAnswer().getAnswerImage();
    }
}
