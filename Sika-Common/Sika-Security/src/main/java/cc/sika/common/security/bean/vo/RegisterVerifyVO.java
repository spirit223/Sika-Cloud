package cc.sika.common.security.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 注册用户数据校验结果VO
 *
 * @author 吴畅
 * @创建时间 2023/3/4 - 19:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVerifyVO {
    private String name;
    private String nick;
    private String pass;
    private String phone;
    private Date birthday;
    private String email;
    private char sex;
}
