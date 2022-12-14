package cc.sika.service.impl;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import cc.sika.api.client.RemoteAnswerService;
import cc.sika.api.client.RemoteQAService;
import cc.sika.api.client.RemoteQuestionService;
import cc.sika.api.common.HttpStatus;
import cc.sika.entity.QuestionAndAnswerExcel;
import cc.sika.exception.NoQuestionException;
import cc.sika.service.DataProcess;
import cc.sika.service.ParsingExcel;
import com.alibaba.excel.EasyExcel;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 22:59
 */
@Service("dataProcess")
@SuppressWarnings("rawtypes")
public class DefaultDataProcess implements DataProcess {

    @Resource
    private RemoteQuestionService remoteQuestionService;
    @Resource
    private RemoteAnswerService remoteAnswerService;

    @Resource
    private RemoteQAService remoteQAService;

    @Resource
    private ParsingExcel excelParser;

    @Override
    public HttpStatus storeQuestion(List<Question> qList) {
        BaseResponse baseResponse = remoteQuestionService.addBatch(qList);
        if (baseResponse.isSuccess()) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    @Override
    public HttpStatus storeQueWithAnswer(List<QuestionAndAnswerExcel> qaeList) {
        List<QuestionWithAnswerBO> qaList = new ArrayList<>();
        qaeList.forEach(excelQA -> {
            if (excelQA.getQuestionId() == null) {
                excelQA.setQuestionId(-1);
            }
            qaList.add(new QuestionWithAnswerBO(excelQA.getQuestionId(), excelQA.getQuestionType(),
                    excelQA.getQuestionContent(), excelQA.getAnswerImage(),
                    excelQA.getQuestionContent(), excelQA.getQuestionTopic(),
                    new Answer(excelQA.getAnswerContent(),
                            excelQA.getAnswerImage(),
                            excelQA.getQuestionId())));
        });
        BaseResponse baseResponse = remoteQAService.addBatch(qaList);
        if (baseResponse.isSuccess()) {
            return HttpStatus.SUCCESS;
        }
        return HttpStatus.ERROR;
    }

    @Override
    public HttpStatus processQuestion(MultipartFile multipartFile) throws IOException {
        List<Question> questionList = excelParser.parseToQuestionPOList(multipartFile);
        if (questionList.isEmpty()) {
            return HttpStatus.UNSUPPORTED_TYPE;
        }
        return storeQuestion(questionList);
    }

    @Override
    public HttpStatus processQueWithAnswer(MultipartFile multipartFile) throws IOException {
        List<QuestionAndAnswerExcel> questionAndAnswerExcels = excelParser.parseToQuestionAndAnswerList(multipartFile);
        if (questionAndAnswerExcels.isEmpty()) {
            return HttpStatus.UNSUPPORTED_TYPE;
        }
        return storeQueWithAnswer(questionAndAnswerExcels);
    }

    @Override
    public List<QuestionAndAnswerExcel> exportQANo2File() throws NoQuestionException {
        BaseResponse<List<QuestionWithAnswerBO>> response = remoteQAService.getAll();
        List<QuestionWithAnswerBO> boList = response.getData();
        if (boList == null || boList.isEmpty()) {
            throw new NoQuestionException();
        }
        return boList.stream().map(QuestionAndAnswerExcel::new).collect(Collectors.toList());
    }

    @Override
    public List<QuestionAndAnswerExcel> exportQA(String filename) throws NoQuestionException, IOException {
        BaseResponse<List<QuestionWithAnswerBO>> response = remoteQAService.getAll();
        File file = new File(filename);
        FileUtils.forceMkdirParent(file);
        if (!file.exists()) {
            boolean isCreatedSuccess = file.createNewFile();
        }
        exportExcel(response.getData(), filename);
        return null;
    }

    public void exportExcel(List<QuestionWithAnswerBO> qaList, String filename) throws NoQuestionException {
        if (qaList == null || qaList.isEmpty()) {
            throw new NoQuestionException();
        }
        List<QuestionAndAnswerExcel> excelList = qaList.stream()
                .map(QuestionAndAnswerExcel::new)
                .collect(Collectors.toList());
        // 写入excel
        EasyExcel.write(filename, QuestionAndAnswerExcel.class).sheet().doWrite(excelList);
    }
}
