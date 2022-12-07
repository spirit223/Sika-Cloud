package cc.sika.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 题目数据持久化对象, 对应 question 表
 * </p>
 *
 * @author 吴畅
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    /**
     * 自动递增id
     */
    private Integer questionId;
    /**
     * 题目类型:
     * <ul>
     *     <li>选择题 -- 1</li>
     *     <li>判断题 -- 2</li>
     *     <li>填空题 -- 3</li>
     *     <li>主观题 -- 4</li>
     * </ul>
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
     * 题目答案
     */
    private String questionAnswer;

    /**
     * 题目分类
     */
    private String questionTopic;

    public Question(char questionType, String questionContent, byte[] questionImage, String questionDescription, String questionAnswer, String questionTopic) {
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.questionImage = questionImage;
        this.questionDescription = questionDescription;
        this.questionAnswer = questionAnswer;
        this.questionTopic = questionTopic;
    }
}
