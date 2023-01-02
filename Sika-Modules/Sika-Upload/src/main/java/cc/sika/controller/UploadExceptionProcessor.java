package cc.sika.controller;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.exception.NoQuestionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 吴畅
 * @创建时间 2023/1/1 - 15:42
 */
@RestController
@RestControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class UploadExceptionProcessor extends BaseController {

    @ExceptionHandler(NoQuestionException.class)
    public BaseResponse noQuestionExceptionHandler(NoQuestionException exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.ACCEPTED, exception.getMessage());
    }
}
