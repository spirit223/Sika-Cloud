package cc.sika.vo;

import cc.sika.api.common.HttpStatus;
import lombok.Data;

/**
 * 从枚举值转换成响应对象返回给前端
 *
 * @author 吴畅
 * @创建时间 2022/12/15 - 15:37
 */
@Data
public class R {
    private String message;
    private int code;

    public R(HttpStatus httpStatus) {
        this.message = httpStatus.getMessage();
        this.code = httpStatus.getCode();
    }
}
