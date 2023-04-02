package cc.sika.common.security.service;

/**
 * @author 吴畅
 * @创建时间 2023/3/4 - 19:29
 */
public interface RegisterService {

    /**
     * 校验用户是否已存在
     *
     * @param username 用户名
     * @return 当用户名合法, 可进行注册时返回true
     */
    boolean checkUser(String username);


}
