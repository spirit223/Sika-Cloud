package cc.sika.common.security.exception;


/**
 * @author 吴畅
 * @创建时间 2022/12/21 - 15:18
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Token非法或已失效!");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
