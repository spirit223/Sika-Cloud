package cc.sika.api.bean.po;

import cc.sika.api.bean.bo.AnswerBO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 对应answer表的数据持久化对象
 * </p>
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 19:35
 */
@Data
@NoArgsConstructor
public class Answer implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer answerId;
    private String answerContent;
    private byte[] answerImage;
    private int qId = -1;


    public Answer(Integer answerId, String answerContent, byte[] answerImage, int qId) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.answerImage = answerImage;
        this.qId = qId;
    }

    public Answer(String answerContent, int qId) {
        this.answerContent = answerContent;
        this.qId = qId;
    }

    public Answer(String answerContent, byte[] answerImage, int qId) {
        this.answerContent = answerContent;
        this.answerImage = answerImage;
        this.qId = qId;
    }

    public Answer(String answerContent, byte[] answerImage) {
        this.answerContent = answerContent;
        this.answerImage = answerImage;
    }

    public Answer(AnswerBO answerBO) {
        this.answerContent = answerBO.getAnswerContent();
        this.answerImage = answerBO.getAnswerImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return qId == answer.qId && answerContent.equals(answer.answerContent) && Arrays.equals(answerImage, answer.answerImage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(answerContent, qId);
        result = 31 * result + Arrays.hashCode(answerImage);
        return result;
    }
}
