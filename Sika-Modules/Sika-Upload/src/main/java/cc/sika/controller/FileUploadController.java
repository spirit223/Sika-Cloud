package cc.sika.controller;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.service.DataProcess;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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
}
