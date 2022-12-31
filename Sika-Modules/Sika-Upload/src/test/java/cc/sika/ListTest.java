package cc.sika;

import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/31 - 16:56
 */
public class ListTest {
    @BeforeEach
    void setUp() {
        List<Question> qList = new ArrayList<>();
        List<Answer> aList = new ArrayList<>();
        qList.add(new Question("aaa", "!111", null, "", -1, ""));
        qList.add(new Question("bbb", "!222", null, "", -1, ""));
        qList.add(new Question("ccc", "!333", null, "", -1, ""));
        aList.add(new Answer("AAA", null, -1));
        aList.add(new Answer("BBB", null, -1));
        aList.add(new Answer("CCC", null, -1));
    }

    @Test
    void testRef() {

    }
}
