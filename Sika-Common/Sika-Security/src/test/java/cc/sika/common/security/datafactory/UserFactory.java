package cc.sika.common.security.datafactory;

import cc.sika.common.security.bean.po.User;

import java.util.Date;

/**
 * 生成测试数据工厂
 *
 * @author 吴畅
 * @创建时间 2022/12/19 - 20:09
 */
public class UserFactory {
    public static User getUser() {
        return getUserSetName("default test user");
    }

    public static User getUserSetName(String username) {
        return new User(username, username, "123456",
                '0', "test@ccmail.com", "13553727721",
                '2', "", '1',
                -1, new Date(System.currentTimeMillis()),
                -1, new Date(System.currentTimeMillis()),
                0);
    }

    public static User getUserWithId(int id, String username) {
        User userSetName = getUserSetName(username);
        userSetName.setId(id);
        return userSetName;
    }
}
