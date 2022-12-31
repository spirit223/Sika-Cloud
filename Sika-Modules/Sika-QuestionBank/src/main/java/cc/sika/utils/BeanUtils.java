package cc.sika.utils;

import java.util.Objects;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 21:05
 */
public class BeanUtils {
    /**
     * 判断传递的参数中是否有包含null
     *
     * @param objects
     * @return
     */
    public static boolean isNull(Object... objects) {
        for (Object object : objects) {
            if (Objects.isNull(object)) {
                return true;
            }
        }
        return false;
    }
}
