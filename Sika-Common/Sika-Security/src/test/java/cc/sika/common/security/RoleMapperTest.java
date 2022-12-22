package cc.sika.common.security;

import cc.sika.common.security.bean.po.Role;
import cc.sika.common.security.datafactory.RoleFactory;
import cc.sika.common.security.mapper.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 21:56
 */
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void testAddOne() {
        roleMapper.addRole(RoleFactory.getRole());
    }

    @Test
    void testAddBatch() {
        Role role1 = RoleFactory.getRoleWithName("测试角色1");
        Role role2 = RoleFactory.getRoleWithName("测试角色2");
        Role role3 = RoleFactory.getRoleWithName("测试角色3");
        List<Role> roles = Arrays.asList(role1, role2, role3);
        roleMapper.addRoleBatch(roles);
    }

    @Test
    void getOne() {
        Role roleById = roleMapper.getRoleById(2);
        System.out.println(roleById);
    }

    @Test
    void getOneByName() {
        Role roleByName = roleMapper.getRoleByName("测试角色3");
        System.out.println(roleByName);
    }

    @Test
    void testGetAll() {
        List<Role> allRole = roleMapper.getAllRole();
        allRole.forEach(System.out::println);
    }

    @Test
    void testDelete() {
        roleMapper.deleteRole(3);
    }

    @Test
    void testDeleteBatch() {
        roleMapper.deleteRoleBatch(Arrays.asList(2, 3, 4));
    }

    @Test
    void testUpdate() {
        roleMapper.updateRole(RoleFactory.getRoleWithId(1, "测试更新角色"));
    }


}
