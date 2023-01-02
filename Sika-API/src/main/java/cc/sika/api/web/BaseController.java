package cc.sika.api.web;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.common.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <p>
 * 基础控制器, 所有控制器返回都使用 {@link BaseResponse} 来存储, 使用泛型 <code>T</code> 来存储真实数据, 并且只有成功返回才会携带数据, 失败的返回都不带泛型
 * </p>
 * <p>
 * 每个控制器都继承该类来完成统一数据处理
 * </p>
 *
 * @author 吴畅
 * @创建时间 2022/12/15 - 19:24
 */
@Slf4j
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    /**
     * 封装成功返回的的方法, 泛型的确定见
     * {@link BaseResponse}
     *
     * @param data VO或DTO, 详见 {@link BaseResponse}
     * @return 默认成功的返回消息对象
     */
    protected <T> BaseResponse<T> responseSuccess(T data) {
        return responseSuccess(data, HttpStatus.SUCCESS.getMessage());
    }

    protected BaseResponse responseSuccess() {
        return responseSuccess(null);
    }

    /**
     * 封装成功返回的方法, 携带成功信息
     *
     * @param data    VO或DTO, 详见 {@link BaseResponse}
     * @param message 要传递的成功信息, 默认为状态码枚举对应信息
     * @return 成功的返回消息对象
     */
    protected <T> BaseResponse<T> responseSuccess(T data, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        if (data instanceof HttpStatus) {
            response.setCode(((HttpStatus) data).getCode());
        } else {
            response.setData(data);
            response.setCode(HttpStatus.SUCCESS.getCode());
        }
        return response;
    }

    /*--------------------------------------------------------------------------*/

    /**
     * 系统内部错误引起的失败返回, 默认500
     * @return 返回500状态码与系统错误提示
     */
    @SuppressWarnings("rawtypes")
    protected BaseResponse responseFailBySystemError() {
        return responseFail(HttpStatus.ERROR);
    }

    /**
     * 通过状态枚举来设置失败返回的消息, 详见 {@link HttpStatus}
     * @param status 详见 {@link HttpStatus}
     * @return 包装了错误信息的失败返回对象
     */
    @SuppressWarnings("rawtypes")
    protected BaseResponse responseFail(HttpStatus status) {
        return responseFail(status, "");
    }

    /**
     * 通过状态枚举和自定义失败信息来设置失败返回的消息
     * @param status status – 详见 {@link HttpStatus}
     * @param message 自定义错误返回消息
     * @return 如果消息为空字符串返回 状态枚举携带的信息
     */
    @SuppressWarnings("rawtypes")
    protected BaseResponse responseFail(HttpStatus status, String message) {
        BaseResponse failResponse = new BaseResponse();
        failResponse.setSuccess(false);
        failResponse.setCode(status.getCode());
        if (StringUtils.hasText(message)) {
            failResponse.setMessage(message);
        } else {
            failResponse.setMessage(status.getMessage());
        }
        return failResponse;
    }

    /**
     * 响应 Excel, 并且在 header 中携带文件名称
     *
     * @param file     excel 文件对象
     * @param response 响应对象
     * @param filename 去除文件路径的文件名
     * @throws IOException
     */
    protected void responseFile(File file, HttpServletResponse response, String filename) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream fileInputStream = new BufferedInputStream(Files.newInputStream(file.toPath()));
        byte[] bytes = new byte[4096];
        int read = 0;
        while ((read = fileInputStream.read(bytes, 0, bytes.length)) != -1) {
            outputStream.write(bytes);
        }
        outputStream.close();
        fileInputStream.close();
    }


    /**
     * 日志记录
     */
    protected void processErrorLog(Exception exception) {
        String requestMethod = request.getMethod();
        if ("GET".equals(requestMethod)) {
            log.error("错误产生URL:{}, 查询参数为: {}", request.getRequestURL(),
                    request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1));
        } else {
            log.error("错误产生URL:{}", request.getRequestURL());
        }
        log.error("异常信息: {}, 异常对象: {}", exception.getMessage(), exception);
        log.error("远程异常ip: {},主机名 {}", request.getRemoteAddr(), request.getRemoteHost());
    }

}
