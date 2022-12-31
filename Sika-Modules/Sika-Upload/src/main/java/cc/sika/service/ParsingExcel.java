package cc.sika.service;

import cc.sika.api.bean.po.Question;
import cc.sika.entity.QuestionAndAnswerExcel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 16:55
 */
public interface ParsingExcel {

    /**
     * 只包含题目的文件解析
     *
     * @param file excel文件
     * @return 从Excel中解析出来的题目列表
     */
    List<Question> parseToQuestionPOList(MultipartFile file) throws IOException;

    /**
     * 带题目和答案的文件解析
     *
     * @param file Excel文件
     * @return 解析出来的题目和答案列表
     */
    List<QuestionAndAnswerExcel> parseToQuestionAndAnswerList(MultipartFile file) throws IOException;

}
