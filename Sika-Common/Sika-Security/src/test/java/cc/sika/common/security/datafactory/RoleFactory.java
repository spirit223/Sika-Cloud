package cc.sika.common.security.datafactory;

import cc.sika.common.security.bean.po.Role;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 21:52
 */
public class RoleFactory {
    public static Role getRole() {
        return getRoleWithName("测试角色");
    }

    public static Role getRoleWithName(String roleName) {
        return new Role(roleName, "sys:test:query", '0', 0, -1, null, -1, null, "");
    }

    public static Role getRoleWithId(int id, String roleName) {
        Role roleWithName = getRoleWithName(roleName);
        roleWithName.setId(id);
        return roleWithName;
    }
}
