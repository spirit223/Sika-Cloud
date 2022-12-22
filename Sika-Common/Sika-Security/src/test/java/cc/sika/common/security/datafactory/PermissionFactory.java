package cc.sika.common.security.datafactory;

import cc.sika.common.security.bean.po.Permission;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 20:52
 */
public class PermissionFactory {

    public static Permission getPermission() {
        return getPermissionWithName("权限测试名称");
    }

    public static Permission getPermissionWithName(String permissionName) {
        return new Permission(permissionName, "权限测试", -1, null, -1, null, '0', "");
    }

    public static Permission getPermissionWithId(int id, String permissionName) {
        Permission permissionWithName = getPermissionWithName(permissionName);
        permissionWithName.setId(id);
        return permissionWithName;
    }
}
