package cc.sika.service;

import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import cc.sika.entity.QuestionAndAnswerExcel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 将excel解析出来的题目或者题目答案数据存入数据库
 *
 * @author 吴畅
 * @创建时间 2022/12/30 - 22:47
 */
public interface DataProcess {
    /**
     * 将Excel中解析出来的题目添加到题库数据库中, 不包含数据解析过程
     *
     * @param qList Question PO 的列表, 见 {@link Question}
     */
    HttpStatus storeQuestion(List<Question> qList);

    /**
     * 将Excel中解析出来的题目和答案添加到题库数据库, 不包含数据解析过程
     *
     * @param qaList 对象中没有答案的questionId字段信息, 需要进行业务匹配
     */
    HttpStatus storeQueWithAnswer(List<QuestionAndAnswerExcel> qaList);

    /**
     * 从 Excel 中解析出来题目并存储进数据库
     *
     * @param multipartFile
     * @return
     */
    HttpStatus processQuestion(MultipartFile multipartFile) throws IOException;

    /**
     * 从 Excel 中解析出来题目和答案并存储进数据库
     *
     * @param multipartFile
     * @return
     */
    HttpStatus processQueWithAnswer(MultipartFile multipartFile) throws IOException;
}
