package cc.sika.common.security;

import cc.sika.common.security.bean.po.Permission;
import cc.sika.common.security.datafactory.PermissionFactory;
import cc.sika.common.security.mapper.PermissionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 20:59
 */
@SpringBootTest
public class PermissionMapperTest {
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    void testAddOne() {
        int addResult = permissionMapper.addPermission(PermissionFactory.getPermission());
        System.out.println("addResult is ==> " + addResult);
    }

    @Test
    void testAddBatch() {
        Permission permission1 = PermissionFactory.getPermissionWithName("测试权限1");
        Permission permission2 = PermissionFactory.getPermissionWithName("测试权限2");
        Permission permission3 = PermissionFactory.getPermissionWithName("测试权限3");
        List<Permission> permissions = Arrays.asList(permission1, permission2, permission3);
        int batchResult = permissionMapper.addPermissionBatch(permissions);
        System.out.println("batchResult is ==> " + batchResult + " and list.size() is " + permissions.size());
    }

    @Test
    void testGetPermissionById() {
        Permission permissionById = permissionMapper.getPermissionById(2);
        System.out.println("permissionById is ==> " + permissionById);
    }

    @Test
    void testGetOneByName() {
        Permission permission = permissionMapper.getPermissionByName("测试权限1");
        System.out.println("permission is ==> " + permission);
    }

    @Test
    void getAllPermission() {
        List<Permission> allPermission = permissionMapper.getAllPermission();
        allPermission.forEach(System.out::println);
    }

    @Test
    void testUpdate() {
        Permission permissionWithId = PermissionFactory.getPermissionWithId(2, "权限测试");
        permissionMapper.updatePermission(permissionWithId);
    }

    @Test
    void testUpdateBatch() {
        Permission permission1 = PermissionFactory.getPermissionWithId(3, "更新权限测试1");
        Permission permission2 = PermissionFactory.getPermissionWithId(4, "更新权限测试2");
        Permission permission3 = PermissionFactory.getPermissionWithId(5, "更新权限测试3");
        List<Permission> permissionList = Arrays.asList(permission1, permission2, permission3);
        int updatePermissionBatch = permissionMapper.updatePermissionBatch(permissionList);
    }

    @Test
    void testChangeDel() {
        int i = permissionMapper.deletePermissionById(3);
    }

    @Test
    void testDeleteBatch() {
        int i = permissionMapper.deletePermissionBatch(Arrays.asList(3, 4, 5));
    }
}
