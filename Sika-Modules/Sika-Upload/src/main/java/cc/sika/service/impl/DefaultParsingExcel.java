package cc.sika.service.impl;

import cc.sika.api.bean.po.Question;
import cc.sika.entity.QuestionAndAnswerExcel;
import cc.sika.service.ParsingExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 16:55
 */
@Service("parsingExcel")
public class DefaultParsingExcel implements ParsingExcel {

    @Override
    public List<Question> parseToQuestionPOList(MultipartFile file) throws IOException {
        List<Question> questionList = new ArrayList<>();
        // 将读取出来的对象集合返回
        EasyExcel.read(file.getInputStream(), Question.class, new PageReadListener<Question>(questionList::addAll)).sheet().doRead();
        return questionList;
    }

    @Override
    public List<QuestionAndAnswerExcel> parseToQuestionAndAnswerList(MultipartFile file) throws IOException {
        List<QuestionAndAnswerExcel> questionWithAnswerBOList = new ArrayList<>();
        EasyExcel.read(file.getInputStream(),
                QuestionAndAnswerExcel.class,
                new PageReadListener<QuestionAndAnswerExcel>(questionWithAnswerBOList::addAll)).sheet().doRead();
        return questionWithAnswerBOList;
    }


}
