package cc.sika.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 基础响应对象, 包含响应的状态信息以及数据
 * </p>
 * <ul>
 *     <li>如果数据需要前端视图渲染泛型使用VO对象</li>
 *     <li>如果数据仅作简单交互泛型使用DTO对象</li>
 * </ul>
 * @author 吴畅
 * @创建时间 2022/12/15 - 18:29
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success;
    private T Data;
    private int code;
    private String message;

}
