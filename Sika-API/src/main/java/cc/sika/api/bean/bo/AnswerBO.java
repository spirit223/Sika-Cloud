package cc.sika.api.bean.bo;

import cc.sika.api.bean.po.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务层对象, 封装 Answer 的基本信息
 * @author 吴畅
 * @创建时间 2022/12/16 - 13:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerBO {
    private String answerContent;
    private byte[] answerImage;

    public AnswerBO(String answerContent) {
        this.answerContent = answerContent;
    }

    public AnswerBO(Answer answer) {
        this.answerContent = answer.getAnswerContent();
        this.answerImage = answer.getAnswerImage();
    }
}
