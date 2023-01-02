package cc.sika.web;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.exception.NoAnswerException;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * 全局异常统一处理
 */
@RestControllerAdvice
@RestController
@Slf4j
@SuppressWarnings("rawtypes")
public class ExceptionAdviceProcessor extends BaseController {



    /**
     * 方法参数解析异常处理, 只对 @Valid 注解验证生效, 详见 {@link javax.validation.Valid}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        processErrorLog(methodArgumentNotValidException);
        return responseFail(HttpStatus.BAD_REQUEST);
    }

    /**
     * 参数格式不匹配, 消息不可读异常处理
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    protected BaseResponse argumentMismatch(Exception exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.BAD_REQUEST);
    }

    /**
     * 缺少参数异常处理
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected BaseResponse missingParameter(MissingServletRequestParameterException exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求方法不支持异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected BaseResponse methodNotSupported(HttpRequestMethodNotSupportedException exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.BAD_METHOD);
    }

    /**
     * 请求的题目id不存在异常处理
     */
    @ExceptionHandler(NoQuestionNumberException.class)
    protected BaseResponse questionIdOufOf(NoQuestionNumberException exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(WriteFailException.class)
    protected BaseResponse writeQuestionFail(WriteFailException exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class})
    protected BaseResponse systemError(Exception exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.ERROR);
    }

    @ExceptionHandler(NoAnswerException.class)
    protected BaseResponse noAnswerException(Exception exception) {
        processErrorLog(exception);
        return responseFail(HttpStatus.NOT_FOUND);
    }
}
