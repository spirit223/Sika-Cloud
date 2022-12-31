package cc.sika;

import cc.sika.api.common.HttpStatus;
import org.junit.jupiter.api.Test;

/**
 * @author 吴畅
 * @创建时间 2022/12/15 - 15:19
 */
public class HttpStatusTest {
    @Test
    void testGetEnumValue() {
        System.out.println(HttpStatus.SUCCESS.name());
        System.out.println(HttpStatus.SUCCESS.ordinal());
        System.out.println(HttpStatus.SUCCESS);
    }

    @Test
    void testGetEnumInfo() {
        System.out.println(HttpStatus.SUCCESS.getMessage());
        System.out.println(HttpStatus.SUCCESS.getCode());
        System.out.println(HttpStatus.ERROR);
    }
}
