package cc.sika.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 17:00
 */
@Mapper
public interface AuthorizationMapper {

    /**
     * 根据用户 id 获取对应的权限信息字符串
     *
     * @param id 用户id
     * @return 权限信息字符串
     */
    List<String> selectPermissionByUserId(@Param("id") int id);

    /**
     * 根据用户名获取权限信息字符串
     *
     * @param username 用户名
     * @return 权限信息字符串
     */
    List<String> selectPermissionByUserName(@Param("username") String username);
}
