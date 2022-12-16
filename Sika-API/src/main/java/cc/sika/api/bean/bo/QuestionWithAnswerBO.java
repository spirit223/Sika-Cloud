package cc.sika.api.bean.bo;

import cc.sika.api.bean.po.Answer;
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
    private char questionType;
    private String questionContent;
    private byte[] questionImage;
    private String questionDescription;
    private String questionTopic;
    private Answer answer;
}
