package cc.sika.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 21:08
 */
@Getter
@Setter
@EqualsAndHashCode
public class QuestionExcel {
    private Integer questionId;
    private String questionType;
    private String questionContent;
    private byte[] questionImage;
    private String questionDescription;
    private Integer answerId;
    private String questionTopic;

    @Override
    public String toString() {
        return "==>QuestionExcel<==";
    }
}
