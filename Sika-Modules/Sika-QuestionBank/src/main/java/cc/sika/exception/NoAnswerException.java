package cc.sika.exception;

/**
 * 没有答案异常
 *
 * @author 吴畅
 * @创建时间 2022/12/23 - 20:19
 */
public class NoAnswerException extends Exception {

    public NoAnswerException() {
        super("没有该答案");
    }

    public NoAnswerException(String message) {
        super(message);
    }
}
