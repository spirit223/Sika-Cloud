package cc.sika.common.security;

import cc.sika.common.security.bean.po.User;
import cc.sika.common.security.datafactory.UserFactory;
import cc.sika.common.security.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 18:27
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User spirit223 = new User("spirit223", "小吴来哩", "wjc52292",
            '0', "2235569493@qq.com", "13553727721",
            '0', "", '0',
            -1, new Date(System.currentTimeMillis()),
            -1, new Date(System.currentTimeMillis()),
            0);

    private User ironMan = new User("ironMan", "铁人", "wjc52292",
            '0', "744981991@qq.com", "13727272464",
            '0', "", '0',
            -1, new Date(System.currentTimeMillis()),
            -1, new Date(System.currentTimeMillis()),
            0);

    @Test
    void testAddOne() {
        int addOneResult = userMapper.addOneUser(spirit223);
        System.out.println("addOneResult is ==> " + addOneResult);
    }

    @Test
    void testGetOneById() {
        User userById = userMapper.getUserById(2);
        System.out.println("userById is ==> " + userById);
    }

    @Test
    void testGetOneByName() {
        User userByName = userMapper.getUserByName("spirit223");
        System.out.println("userByName is ==> " + userByName);
    }

    @Test
    void testGetOneByNickName() {
        List<User> userByNickName = userMapper.getUserByNickName("小吴来哩");
        System.out.println("userByNickName is ==> " + userByNickName);
    }

    @Test
    void testAddBatch() {
        User user1 = UserFactory.getUserSetName("测试用户1");
        User user2 = UserFactory.getUserSetName("测试用户2");
        User user3 = UserFactory.getUserSetName("测试用户3");
        List<User> userList = Arrays.asList(user1, user2, user3);
        int addUserListResult = userMapper.addUserList(userList);
        System.out.println("addUserListResult is ==> " + addUserListResult + " and list.size() is " + userList.size());
    }

    @Test
    void testGetListByStatus() {
        List<User> userByStatusList = userMapper.getUserByStatus('0');
        userByStatusList.forEach(item -> System.out.println("In userByStatusList item is ==> " + item));
    }

    @Test
    void testGetAllUser() {
        List<User> allUser = userMapper.getAllUser();
        allUser.forEach(item -> System.out.println("In allUser item is ==> " + item));
    }

    @Test
    void testUpdateDelFlag() {
        int deleteResult = userMapper.deleteUserById(6);
        System.out.println("deleteResult is ==> " + deleteResult);
    }

    @Test
    void testUpdateDelFlagByList() {
        List<Integer> idList = Arrays.asList(6, 7, 8);
        int deleteUserByListResult = userMapper.deleteUserByList(idList);
        System.out.println("deleteUserByListResult is ==> " + deleteUserByListResult + " and list.size() is " + idList.size());
    }

    @Test
    void testTruncateUserById() {
        int deleteResult = userMapper.truncateUserById(6);
        System.out.println("deleteResult is ==> " + deleteResult);
    }

    @Test
    void testTruncateUserBatch() {
        List<Integer> list = Arrays.asList(7, 8);
        int batchDeleteResult = userMapper.truncateUserBatch(list);
        System.out.println("batchDeleteResult is ==> " + batchDeleteResult + " and list.size() is " + list.size());
    }

    @Test
    void testUpdateUser() {
        User user = UserFactory.getUserSetName("测试更新用户2");
        user.setId(10);
        user.setPassword("654321");
        user.setUpdateTime(null);
        int updateResult = userMapper.updateUser(user);
        System.out.println("updateResult is ==> " + updateResult);
    }

    @Test
    void testUpdateBatch() {
        User user1 = UserFactory.getUserSetName("测试更新用户2");
        user1.setId(9);
        user1.setPassword("654321");
        User user2 = UserFactory.getUserSetName("测试更新用户2");
        user2.setId(10);
        user2.setPassword("654321");
        User user3 = UserFactory.getUserSetName("测试更新用户2");
        user3.setId(11);
        user3.setPassword("654321");
        List<User> userList = Arrays.asList(user1, user2, user3);
        int batchUserResult = userMapper.updateBatchUser(userList);
        System.out.println("batchUserResult is ==> " + batchUserResult + " and list.size() is " + userList.size());
    }

    @Test
    void testChangePassword() {
        String encode = passwordEncoder.encode("wjc52292");
        String defaultPassword1 = passwordEncoder.encode("123456");
        String defaultPassword2 = passwordEncoder.encode("123456");
        String defaultPassword3 = passwordEncoder.encode("123456");
        userMapper.updatePassword(2, encode);
        userMapper.updatePassword(9, defaultPassword1);
        userMapper.updatePassword(10, defaultPassword2);
        userMapper.updatePassword(11, defaultPassword3);
    }
}
