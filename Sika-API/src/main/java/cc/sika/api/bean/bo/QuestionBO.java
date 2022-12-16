package cc.sika.api.bean.bo;

import cc.sika.api.bean.po.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 业务层对象, 封装 Question 的基本信息
 *
 * @author 吴畅
 * @创建时间 2022/12/16 - 13:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBO {
    /**
     * 题目类型
     */
    private char questionType;
    /**
     * 题目内容
     */
    private String questionContent;
    /**
     * 题目图片, 如果需要图片将图片转换成二进制字节数组
     */
    private byte[] questionImage;
    /**
     * 题目描述
     */
    private String questionDescription;
    /**
     * 题目分类
     */
    private String questionTopic;

    public QuestionBO(Question question) {
        this.questionType = question.getQuestionType();
        this.questionContent = question.getQuestionContent();
        this.questionImage = question.getQuestionImage();
        this.questionDescription = question.getQuestionDescription();
        this.questionTopic = question.getQuestionTopic();
    }
}
