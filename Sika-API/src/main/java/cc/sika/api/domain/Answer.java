package cc.sika.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private int answerId;
    private String answerContent;
    private int questionId;

    public Answer(String answerContent, int questionId) {
        this.answerContent = answerContent;
        this.questionId = questionId;
    }
}
