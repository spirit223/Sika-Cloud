package cc.sika.common.security.mapper;

import cc.sika.common.security.bean.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定义数据库 sys_user 表的 CRUD 操作
 *
 * @author 吴畅
 * @创建时间 2022/12/19 - 13:22
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户id查询用户实体信息
     *
     * @param id 用户id
     * @return 如果用户存在, 返回整个用户的信息po对象, 如果用户不存在返回null
     */
    User getUserById(@Param("id") int id);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 如果用户存在, 返回整个用户的信息po对象, 如果用户不存在返回null
     */
    User getUserByName(@Param("name") String name);

    /**
     * 根据用户昵称获取用户信息, 昵称可以重复所以会得到一个列表
     *
     * @param nickName 用户昵称
     * @return 返回多个同名昵称的 User po对象
     */
    List<User> getUserByNickName(@Param("nickName") String nickName);

    /**
     * 根据状态查询用户信息, 允许使用的账户以及黑名单用户查询
     *
     * @param status 用户状态, 查询黑名单时为 1, 查询正常用户为 0
     * @return 返回符合状态的User列表, 如果不存在则为空列表
     */
    List<User> getUserByStatus(@Param("status") char status);

    /**
     * 获取所有用户信息
     *
     * @return 用户信息列表
     */
    List<User> getAllUser();


    /*----------------------------------------------------------------------*/

    /**
     * 通过 User po实体对象添加用户信息到数据库
     *
     * @param user 封装了用户信息的实体对象, 见 {@link User}
     * @return 插入成功返回 1, 插入失败返回 0
     */
    int addOneUser(User user);

    /**
     * 通过 User 列表批量插入多个用户
     *
     * @param userList 存储多个用户信息的对象列表
     * @return 返回插入成功的数量, 插入数量与列表大小相等时为插入正常
     */
    int addUserList(List<User> userList);

    /*----------------------------------------------------------------------*/

    /**
     * 更新用户信息, 该方式根据传入的 User对象id判定要更新的对象,
     * 将其他属性覆盖原有数据
     *
     * @param user 携带要修改用户id和更新信息的实体对象, 请检查每个字段的值
     * @return 更新成功时为 1, 失败时为 0
     */
    int updateUser(User user);

    /**
     * 批量更新用户信息, 将列表中每一个实体id对应到数据库每行数据,
     * 然后将实体对象的其他属性覆盖数据库中已经存在的数据
     *
     * @param userList 要更新的用户信息列表
     * @return 返回更新成功的数量, 当插入成功数量与列表大小一致时为插入正常
     */
    int updateBatchUser(List<User> userList);

    /**
     * 修改密码, 通过用户id匹配用户后覆盖密码
     *
     * @param userId   用户id
     * @param password 新的密码
     * @return 修改成功返回1, 失败返回0
     */
    int updatePassword(@Param("userId") int userId, @Param("password") String password);

    /*----------------------------------------------------------------------*/

    /**
     * <p>
     * 通过用户id删除用户
     * </p>
     * <b>
     * 注意: 这不是一个数据删除的方法,
     * 这个删除方式只是将用户的删除标志位修改为已删除,
     * <em>不会删除真实数据</em>
     * </b>
     *
     * @param id 要删除的用户id
     * @return 返回删除成功的对象
     */
    int deleteUserById(@Param("id") int id);

    /**
     * <p>
     * 将列表中所有的用户对象删除标志修改为 已删除(delFlag = 1),
     * </p>
     * <b>不会删除真实数据</b>
     *
     * @param userIdList 要删除的用户列表
     * @return 返回修改成功的数量, 如果每个实体对象的id都存在于数据库, 则正常删除的结果应与列表大小一致
     */
    int deleteUserByList(List<Integer> userIdList);

    /**
     * <p>通过用户id删除用户信息, 该将直接删除数据库用户信息</p>
     * <b>修改用户删除标志请使用 <code>UserMapper.deleteUserById(int id)</code></b>
     *
     * @param id 要删除的用户id
     * @return 成功删除时返回1, 失败返回0
     */
    int truncateUserById(@Param("id") int id);

    /**
     * 根据用户id列表删除用户信息, 该方法将删除数据库中id匹配的所有真实数据
     *
     * @param userIdList 用户id列表
     * @return 如果每个id都匹配, 成功删除结果应与列表大小一致
     */
    int truncateUserBatch(List<Integer> userIdList);
}
