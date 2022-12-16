package cc.sika.exception;

/**
 * @author 吴畅
 * @创建时间 2022/12/15 - 16:43
 */
public class NoQuestionNumberException extends Exception {

    public NoQuestionNumberException(String message) {
        super(message);
    }

    public NoQuestionNumberException(int id) {
        super("There is no question with number: " + id);
    }
}
