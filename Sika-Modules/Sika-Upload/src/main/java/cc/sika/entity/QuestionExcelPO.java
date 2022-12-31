package cc.sika.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 字段和表头对应示例实体类
 * @author 吴畅
 * @创建时间 2022/12/30 - 21:08
 */
@Getter
@Setter
@EqualsAndHashCode
public class QuestionExcelPO {
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
    @ExcelProperty("answer_id")
    private Integer answerId;
    @ExcelProperty("question_topic")
    private String questionTopic;

    @Override
    public String toString() {
        return "QuestionExcelPO{" +
                "questionContent='" + questionContent + '\'' +
                '}';
    }
}
