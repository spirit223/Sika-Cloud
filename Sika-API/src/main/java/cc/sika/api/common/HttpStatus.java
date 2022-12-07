package cc.sika.api.common;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 13:22
 */
public enum HttpStatus {
    SUCCESS("操作成功", 200),
    CREATED("对象创建成功", 201),
    ACCEPTED("请求已经被接受", 202),
    NO_CONTENT("操作已经执行成功，但是没有返回数据", 204),
    MOVED_PERM("资源已被移除", 301),
    SEE_OTHER("重定向", 303),
    NOT_MODIFIED("资源没有被修改", 304),
    BAD_REQUEST("参数列表错误（缺少，格式不匹配）", 400),
    UNAUTHORIZED("未授权", 401),
    FORBIDDEN("访问受限，授权过期", 403),
    NOT_FOUND("资源，服务未找到", 404),
    BAD_METHOD("不允许的http方法", 405),
    CONFLICT("资源冲突，或者资源被锁", 409),
    UNSUPPORTED_TYPE("不支持的数据，媒体类型", 415),
    ERROR("系统内部错误", 500),
    NOT_IMPLEMENTED("接口未实现", 501);

    HttpStatus(String s, int i) {

    }
}
