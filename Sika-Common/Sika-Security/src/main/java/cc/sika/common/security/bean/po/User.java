package cc.sika.common.security.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应sys_user表的数据持久化对象
 * @author 吴畅
 * @创建时间 2022/12/19 - 13:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     *
     * 密码
     */
    private String password;
    /**
     * 账户状态(0正常 1停用)
     */
    private char status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 性别:
     * <ul>
     * <li>0 男</li>
     * <li>1 女</li>
     * <li>2 未知</li>
     * </ul>
     */
    private char sex;
    /**
     * 头像, 存储图片路径
     */
    private String charHead;
    /**
     * 用户类型
     * <ul>
     *  <li>0 管理员</li>
     *  <li>1 普通用户</li>
     * </ul>
     */
    private char userType;
    /**
     * 创建人用户id
     */
    private int createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人用户id
     */
    private int updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志
     * <ul>
     *     <li>0 未删除</li>
     *     <li>1 已删除</li>
     * </ul>
     */
    private int delFlag;

    public User(String username, String nickName, String password, char status, String email, String phoneNumber, char sex, String charHead, char userType, int createBy, Date createTime, int updateBy, Date updateTime, int delFlag) {
        this.username = username;
        this.nickName = nickName;
        this.password = password;
        this.status = status;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.charHead = charHead;
        this.userType = userType;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }
}
