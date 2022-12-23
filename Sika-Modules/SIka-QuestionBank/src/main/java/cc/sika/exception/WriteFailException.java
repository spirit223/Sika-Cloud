package cc.sika.exception;

/**
 * 数据库写入失败异常
 *
 * @author 吴畅
 * @创建时间 2022/12/15 - 21:34
 */
public class WriteFailException extends Exception {
    public WriteFailException(String message) {
        super(message);
    }

    public WriteFailException() {
        super("数据写入失败");
    }
}
