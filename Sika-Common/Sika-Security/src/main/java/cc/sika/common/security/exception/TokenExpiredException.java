package cc.sika.common.security.exception;

/**
 * @author 吴畅
 * @创建时间 2023/2/4 - 12:34
 */
public class TokenExpiredException extends Exception {
    private String message;

    public TokenExpiredException() {
        this.message = "Token已过期";
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
