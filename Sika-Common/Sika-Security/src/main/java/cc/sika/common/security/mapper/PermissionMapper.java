package cc.sika.common.security.mapper;

import cc.sika.common.security.bean.po.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 14:05
 */
@Mapper
public interface PermissionMapper {
    /**
     * 根据权限id获取权限
     * @param id 权限id
     * @return 返回id对应的 {@link Permission} 对象, 权限不存在时返回null
     */
    Permission getPermissionById(int id);

    /**
     * 根据权限名称获取权限
     * @param permissionName 权限名称
     * @return 返回权限名称对应的 {@link Permission} 对象, 权限不存在时返回 null
     */
    Permission getPermissionByName(@Param("permissionName") String permissionName);

    /**
     * 获取所有的权限信息并封装成一个列表
     * @return 权限信息列表
     */
    List<Permission> getAllPermission();

    /*----------------------------------------------------------------------*/

    /**
     * 根据 {@link Permission} 对象, 添加 permission 列信息到数据库
     * @param permission {@link Permission} 对象
     * @return 添加成功时返回1, 失败返回0
     */
    int addPermission(Permission permission);

    /**
     * 根据 {@link Permission} 列表添加多行 permission 列数据到数据库
     * @param permissionList {@link Permission} 对象列表
     * @return 返回插入成功的数量, 正常插入时, 插入结果应与列表大小相同
     */
    int addPermissionBatch(List<Permission> permissionList);

    /*----------------------------------------------------------------------*/

    /**
     * <p>
     * 根据id删除权限
     * </p>
     * <b>该方法只将行数据的删除标志位修改为 1, 不会删除真实的数据</b>
     * @param id 要删除的权限id
     * @return 成功修改时返回1, 失败返回0
     */
    int deletePermissionById(@Param("permissionId") int id);

    /**
     * <p>
     *     根据权限id列表批量修改删除标志位为1
     * </p>
     * @param permissionIdList 要删除的权限id列表
     * @return 返回修改删除标志成功的数量, 如果列表中的id与行数据都能匹配, 插入成功的返回结果应当与列表大小相同
     */
    int deletePermissionBatch(List<Integer> permissionIdList);

    /**
     * <p>
     *     根据权限id删除权限信息, 该方法是删除真实数据的方法
     * </p>
     * <b>
     *     如果只是修改删除标志位, 保留权限数据应当使用
     *     <code>
     *         PermissionMapper.deletePermissionById(int id);
     *     </code>
     * </b>
     * @param id 要删除数据的权限id
     * @return 成功删除返回1, 失败返回0
     */
    int truncatePermissionById(@Param("id") int id);

    /**
     * <p>
     * 根据权限id列表删除权限信息, 该方法是删除真实数据的方法
     * </p>
     * <b>
     *     如果只是修改删除标志位, 保留权限数据应当使用
     *     <code>
     *         PermissionMapper.deletePermissionBatch(List<Integer> permissionIdList);
     *     </code>
     * </b>
     * @param permissionIdList 权限id列表
     * @return 返回修改删除标志成功的数量, 如果列表中的id与行数据都能匹配, 插入成功的返回结果应当与列表大小相同
     */
    int truncatePermissionBatch(List<Integer> permissionIdList);

    /*----------------------------------------------------------------------*/

    /**
     * 根据 {@link Permission} po 对象的 id匹配要修改的行数据后将 po 对象的其他属性覆盖行数据其他列
     * @param permission 携带要修改的 id以及要修改的数据的 {@link Permission} po 对象
     * @return 成功修改返回1, 失败返回0
     */
    int updatePermission(Permission permission);

    /**
     * 根据 Permission po 对象列表中每一项的 id匹配要修改的行数据后将 po 对象的其他属性覆盖行数据其他列
     * @param permissionList 携带要修改的 id以及要修改的数据的 Permission po 对象列表
     * @return 修改成功的数量, 如果每个id都能够匹配, 则修改结果应当与列表大小一致
     */
    int updatePermissionBatch(List<Permission> permissionList);
}
