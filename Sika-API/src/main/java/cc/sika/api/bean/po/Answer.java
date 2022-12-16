package cc.sika.api.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 对应answer表的数据持久化对象
 * </p>
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int answerId;
    private String answerContent;
    private byte[] answerImage;
    private int questionId = -1;

    public Answer(String answerContent, int questionId) {
        this.answerContent = answerContent;
        this.questionId = questionId;
    }

    public Answer(String answerContent, byte[] answerImage, int questionId) {
        this.answerContent = answerContent;
        this.answerImage = answerImage;
        this.questionId = questionId;
    }

    public Answer(String answerContent, byte[] answerImage) {
        this.answerContent = answerContent;
        this.answerImage = answerImage;
    }

}
