package cc.sika.common.security.service;

import cc.sika.api.bean.bo.UserInfo;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.common.security.bean.po.User;

/**
 * @author 吴畅
 * @创建时间 2022/12/21 - 12:45
 */
public interface LoginService {

    /**
     * 登录接口, 该接口将认证传入的用户是否合法, 如果认证成功返回成功消息以及token, 同时将用户登录信息缓存
     *
     * @param user 登录的用户对象
     * @return BaseResponse封装消息以及token
     */
    BaseResponse<Object> login(User user);

    /**
     * 退出登录接口, 将缓存数据清空, 从SecurityContext中清除用户验证状态
     *
     * @return 退出消息
     */
    BaseResponse<Object> logout();

    UserInfo checkUserInfo(String token);
}
