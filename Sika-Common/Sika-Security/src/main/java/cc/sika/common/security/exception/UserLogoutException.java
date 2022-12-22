package cc.sika.common.security.exception;


/**
 * @author 吴畅
 * @创建时间 2022/12/22 - 0:41
 */
public class UserLogoutException extends RuntimeException {
    public UserLogoutException(String explanation) {
        super(explanation);
    }

    public UserLogoutException() {
        super("用户未登录(已注销)");
    }
}
