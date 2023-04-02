package cc.sika.exception;

/**
 * @author 吴畅
 * @创建时间 2023/2/2 - 20:34
 */
public class NoNameException extends RuntimeException {
    public NoNameException() {
        super("未指定文件名或文件名为空");
    }

    public NoNameException(String message) {
        super(message);
    }
}
