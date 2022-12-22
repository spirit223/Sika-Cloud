package cc.sika.common.security.mapper;

import cc.sika.common.security.bean.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/19 - 14:21
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据角色id获取角色信息对象 {@link Role}
     *
     * @param id {@link Integer} 角色id
     * @return 角色信息 po 对象, 如果id不存在则返回 null
     */
    Role getRoleById(@Param("id") int id);

    /**
     * 根据角色名称获取角色信息对象 {@link Role}
     *
     * @param roleName {@link String} 要获取的角色名称
     * @return 返回角色名称对应的角色信息po对象 {@link Role}, 如果名称不存在则返回 null
     */
    Role getRoleByName(@Param("roleName") String roleName);

    /**
     * 获取所有的角色信息
     *
     * @return 所有的角色信息列表
     */
    List<Role> getAllRole();

    /*----------------------------------------------------------------------*/

    /**
     * 添加角色信息, 原子操作<b>不校验角色是否已经存在</b>
     *
     * @param role 角色信息 po对象 {@link Role}
     * @return 插入成功时返回1, 失败返回0
     */
    int addRole(Role role);

    /**
     * 批量添加 {@link Role} 信息到数据库, <b>不校验角色的唯一性</b>
     *
     * @param roleList Role信息的列表
     * @return 插入成功的数量, 正常插入时, 结果应与列表大小相同
     */
    int addRoleBatch(List<Role> roleList);

    /*----------------------------------------------------------------------*/

    /**
     * 根据角色id修改删除标志位为1
     *
     * @param id 要修改为删除状态的角色id
     * @return 成功修改时返回1, 失败返回0
     */
    int deleteRole(@Param("id") int id);

    /**
     * 根据列表中的id, 将匹配的所有角色删除标志位修改为1
     *
     * @param roleIdList 要修改为删除状态的角色id列表
     * @return 成功修改删除的数量, 正常修改删除的结果应当与列表大小一致
     */
    int deleteRoleBatch(List<Integer> roleIdList);

    /**
     * <p>
     * 根据角色id删除真实数据
     * </p>
     * <b>
     * 修改删除标志位使用
     * <code>
     * RoleMapper.deleteRole(int id);
     * </code>
     * </b>
     *
     * @param id 要删除的角色id
     * @return 成功删除时返回1, 失败为0
     */
    int truncateRole(@Param("id") int id);

    /**
     * 根据列表中的id, 将匹配的所有角色数据删除
     *
     * @param roleIdList 要除的角色id列表
     * @return 成功删除的数量, 正常删除的结果应当与列表大小一致
     */
    int truncateRoleBatch(List<Integer> roleIdList);

    /*----------------------------------------------------------------------*/

    /**
     * 更新角色信息, 该方法根据 {@link Role} po 对象的id匹配要修改的角色信息, 将其他字段覆盖数据
     *
     * @param role 携带要修改的角色id与要修改的数据的 {@link Role} po对象
     * @return 修改成功返回1, 失败为0
     */
    int updateRole(Role role);

    /**
     * 批量更新角色信息, 该方法根据列表中所有的 {@link Role} po 对象的id匹配要修改的角色信息, 将其他字段覆盖数据
     *
     * @param roleList 携带要修改的角色id与要修改的数据的 {@link Role} po对象列表
     * @return 成功修改的数量, 如果每个角色id都匹配, 结果应当与列表大小相同
     */
    int updateRoleBatch(List<Role> roleList);
}
