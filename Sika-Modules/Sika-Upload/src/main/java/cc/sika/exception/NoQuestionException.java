package cc.sika.exception;

/**
 * @author 吴畅
 * @创建时间 2023/1/1 - 15:40
 */
public class NoQuestionException extends Exception {
    public NoQuestionException() {
        super("题库没有题目");
    }

    public NoQuestionException(String message) {
        super(message);
    }
}
