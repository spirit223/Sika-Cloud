package cc.sika.controller;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.entity.GenericFileVO;
import cc.sika.entity.QuestionAndAnswerExcel;
import cc.sika.exception.NoQuestionException;
import cc.sika.service.DataProcess;
import cc.sika.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 16:49
 */
@RestController
@RequestMapping("/file")
@SuppressWarnings("rawtypes")
public class FileUploadController extends BaseController {

    @Resource
    private DataProcess dataProcessor;
    @Resource
    private StorageService storageService;

    @PostMapping("/upload/question")
    public BaseResponse uploadQuestion(@RequestParam("file") MultipartFile file) throws IOException {
        HttpStatus httpStatus = dataProcessor.processQuestion(file);
        if (httpStatus == HttpStatus.SUCCESS) {
            return responseSuccess(null, "题目添加成功");
        }
        return responseFail(httpStatus);
    }

    @PostMapping("/upload/qa")
    public BaseResponse uploadQA(@RequestBody MultipartFile file) throws IOException {
        HttpStatus httpStatus = dataProcessor.processQueWithAnswer(file);
        if (httpStatus == HttpStatus.SUCCESS) {
            return responseSuccess(null, "题目和答案添加成功");
        }
        return responseFail(httpStatus);
    }

    @GetMapping("/qa/copy")
    public BaseResponse<List<QuestionAndAnswerExcel>> downloadQA() throws NoQuestionException {
        List<QuestionAndAnswerExcel> qae = dataProcessor.exportQANo2File();
        return responseSuccess(qae, "题库获取成功");
    }

    @GetMapping("/qa-excel")
    public void getExcel(HttpServletResponse response) throws NoQuestionException, IOException {
        String formatDate = new SimpleDateFormat("yyyy-MM-dd-ss-SS").format(new Date());
        // todo 文件输出路径
        String filename = "D:/sika/file/qa" + formatDate + ".xlsx";
        String name = FilenameUtils.getName(filename);
        dataProcessor.exportQA(filename);
        responseExcelFile(new File(filename), response, name);
    }

    /**
     * 处理文件上传
     */
    @PostMapping("/general")
    public void uploadFile(MultipartFile file) throws IOException {
        storageService.store(file);
    }

    @GetMapping("/all-files")
    public List<GenericFileVO> allFiles() throws IOException {
        return storageService.loadAll();
    }
}
